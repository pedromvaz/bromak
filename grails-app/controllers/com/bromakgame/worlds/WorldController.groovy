package com.bromakgame.worlds

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class WorldController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond World.list(params), model:[worldCount: World.count()]
	}

	@Secured('ROLE_UNKNOWN')
	def show(World world) {
		respond world
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

		if (world.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond world.errors, view:'create'
			return
		}

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
}
