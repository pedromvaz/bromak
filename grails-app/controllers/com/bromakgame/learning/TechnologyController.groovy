package com.bromakgame.learning

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class TechnologyController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	@Secured('ROLE_UNKNOWN')
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Technology.list(params), model:[technologyCount: Technology.count()]
	}

	@Secured('ROLE_UNKNOWN')
	def show(Technology technology) {
		respond technology
	}

	def create() {
		respond new Technology(params)
		
		if (session) {
			session["epochId"] = params.epochId
		}
	}

	@Transactional
	def save(Technology technology) {
		if (technology == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (technology.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond technology.errors, view:'create'
			return
		}

		technology.save()
		
		if (session && session["epochId"]) {
			Epoch epoch = Epoch.get(session["epochId"])
			
			epoch.add(technology)
			epoch.save(flush:true)
		}

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'technology.label', default: 'Technology'), technology.id])
				redirect controller:"epoch", action:"index", method:"GET"
			}
			'*' { respond technology, [status: CREATED] }
		}
	}

	@Secured('ROLE_UNKNOWN')
	def edit(Technology technology) {
		respond technology
	}

	@Secured('ROLE_UNKNOWN')
	@Transactional
	def update(Technology technology) {
		if (technology == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (technology.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond technology.errors, view:'edit'
			return
		}

		technology.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'technology.label', default: 'Technology'), technology.id])
				redirect technology
			}
			'*'{ respond technology, [status: OK] }
		}
	}

	@Secured('ROLE_UNKNOWN')
	@Transactional
	def delete(Technology technology) {

		if (technology == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		technology.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'technology.label', default: 'Technology'), technology.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'technology.label', default: 'Technology'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
