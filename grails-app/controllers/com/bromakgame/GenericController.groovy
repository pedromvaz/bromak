package com.bromakgame

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
abstract class GenericController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	abstract String getDomainClassName();

	abstract def index(Integer max);

	def show(Object object) {
		respond object
	}

	abstract def create();

	@Transactional
	def save(Object object) {
		if (object == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		
		completeBeforeSaving(object)
		
		object.validate()

		if (object.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond object.errors, view:'create'
			return
		}

		object.save(flush:true)

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [getDomainClassName(), object.id])
				redirectAfterSave()
			}
		}
	}
	
	abstract void completeBeforeSaving(Object object);
	
	void redirectAfterSave() {
		redirect action: "index", method: "GET"
	}

	def edit(Object object) {
		respond object
	}

	@Transactional
	def update(Object object) {
		if (object == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (object.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond object.errors, view:'edit'
			return
		}

		object.save(flush:true)

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [getDomainClassName(), object.id])
				redirectAfterUpdate()
			}
		}
	}

	void redirectAfterUpdate() {
		redirect action: "index", method: "GET"
	}

	@Transactional
	def delete(Object object) {

		if (object == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		object.delete(flush:true)

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [getDomainClassName(), object.id])
				redirectAfterDelete()
			}
		}
	}

	void redirectAfterDelete() {
		redirect action:"index", method:"GET"
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [getDomainClassName(), params.id])
				redirect action: "index", method: "GET"
			}
		}
	}
}
