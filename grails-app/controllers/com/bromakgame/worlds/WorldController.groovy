package com.bromakgame.worlds

import static org.springframework.http.HttpStatus.*
import com.bromakgame.creatures.Champion
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

	@Secured('ROLE_PLAYER')
	def show(World world) {
		
		// despicable hack to point to tutorials page in case it is a tutorial world
		if (message(code: 'tutorials.world.name').equals(world?.name)) {
			redirect action: 'tutorials'
		}
		
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
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'worlds.label'), params.id])
				redirect action: "index", method: "GET"
			}
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
		String worldName = message(code: 'tutorials.world.name')
		
		World tutorials = World.findByNameAndOwner(worldName, player)
		
		if (!tutorials) {
			tutorials = new World(name: worldName, radius: 0, maxNumPlayers: 1)
			
			if (!saveOrUpdate(tutorials, true)) {
				return
			}
		}
		
		Champion champion = Champion.findByWorld(tutorials)
		
		// must set the session variable for the [possible] champion creation
		session["worldId"] = tutorials.id
		
		respond tutorials, model : [hasChampion : champion != null]
	}
}
