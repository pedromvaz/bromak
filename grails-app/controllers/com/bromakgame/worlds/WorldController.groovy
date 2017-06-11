package com.bromakgame.worlds

import static org.springframework.http.HttpStatus.*
import com.bromakgame.users.User
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class WorldController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	transient springSecurityService

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond World.list(params), model:[worldCount: World.count()]
	}

	def show(World world) {
		def regions = Region.findAllByWorld(world)
		def areas = Area.findAllByRegionInList(regions)

		//println("Found " + Region.countByWorld(world))
		//println("Found " + Area.countByRegionInList(regions))

		respond world, model: [ areas: areas, regions: regions ]
	}

	def create() {
		respond new World(params)
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PLAYER'])
	@Transactional
	def save(World world) {
		if (!saveOrUpdate(world, true)) {
			return
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'worlds.label'), world.id])
				redirect action:"index", method:"GET"
			}
			'*' { respond world, [status: CREATED] }
		}
	}

	@Secured('ROLE_UNKNOWN')
	def edit(World world) {
		respond world
	}

	@Secured('ROLE_UNKNOWN')
	@Transactional
	def update(World world) {
		if (!saveOrUpdate(world, false)) {
			return
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'worlds.label'), world.id])
				redirect world
			}
			'*'{ respond world, [status: OK] }
		}
	}

	@Secured(['ROLE_ADMIN', 'ROLE_PLAYER'])
	@Transactional
	private boolean saveOrUpdate(World world, boolean isCreate) {
		if (world == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return false
		}

		// in order to associate User with World
		// it must be done before hasErrors()
		// and a new validate() must be run to validate User is now set
		// hasErrors() will use result from last validate()
		if (isCreate) {
			User user = springSecurityService?.getCurrentUser()

			if (user != null) {
				world.owner = user
				world.validate()
			}
		}

		if (world.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond world.errors, view: (isCreate ? 'create' : 'edit')
			return false
		}

		if (isCreate) {
			world.generate()
		}

		world.save(flush:true)
		
		return true
	}

	@Secured('ROLE_UNKNOWN')
	@Transactional
	def delete(World world) {

		if (world == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		world.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'worlds.label'), world.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'worlds.label'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}

	@Secured('ROLE_PLAYER')
	def choose() {
		User player = springSecurityService?.getCurrentUser()
		
		def single_results = World.findAllByOwner(player, [sort: "name"])
		def multi_results = World.findAllByMaxNumPlayersGreaterThan(1, [sort: "name"])
		
		boolean startedTutorials = World.countByNameAndOwner(message(code: 'tutorials.world.name'), player) > 0
		
		def model = [ singleplayer : single_results ]
		model << [ multiplayer : multi_results ]
		model << [ startedTutorials : startedTutorials ]
		
		respond single_results, model : model
	}

	@Secured('ROLE_PLAYER')
	@Transactional
	def tutorials() {
		User player = springSecurityService?.getCurrentUser()
		
		World tutorials = World.findByNameAndOwner(message(code: 'tutorials.world.name'), player)
		
		if (!tutorials) {
			tutorials = new World(name: "Tutorials", radius: 0, maxNumPlayers: 1)
			
			if (!saveOrUpdate(tutorials, true)) {
				return
			}
		}
		
		respond tutorials
	}
}
