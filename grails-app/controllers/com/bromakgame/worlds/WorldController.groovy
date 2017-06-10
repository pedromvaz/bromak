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

		respond world, model: [ areas: areas ]
	}

	def create() {
		respond new World(params)
	}

	@Transactional
	def save(World world) {
		if (world == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		// in order to associate User with World
		// it must be done before hasErrors()
		// and a new validate() must be run to validate User is now set
		// hasErrors() will use result from last validate()
		User user = springSecurityService?.getCurrentUser()

		if (user != null) {
			world.owner = user
			world.validate()
		}

		if (world.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond world.errors, view:'create'
			return
		}

		world.generate()

		world.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'world.label', default: 'World'), world.id])
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
		if (world == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (world.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond world.errors, view:'edit'
			return
		}

		world.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'world.label', default: 'World'), world.id])
				redirect world
			}
			'*'{ respond world, [status: OK] }
		}
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
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'world.label', default: 'World'), world.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'world.label', default: 'World'), params.id])
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
		
		respond single_results, model : [ singleplayer : single_results, multiplayer : multi_results ]
	}
}
