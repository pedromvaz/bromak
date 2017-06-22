package com.bromakgame.learning

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class SkillCategoryController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond SkillCategory.list(params), model:[skillCategoryCount: SkillCategory.count()]
	}

	def show(SkillCategory skillCategory) {
		respond skillCategory
	}

	def create() {
		respond new SkillCategory(params)
	}

	@Transactional
	def save(SkillCategory skillCategory) {
		if (skillCategory == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (skillCategory.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond skillCategory.errors, view:'create'
			return
		}

		skillCategory.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'skillCategory.label', default: 'SkillCategory'), skillCategory.id])
				redirect action:"index", method:"GET"
			}
		}
	}

	def edit(SkillCategory skillCategory) {
		respond skillCategory
	}

	@Transactional
	def update(SkillCategory skillCategory) {
		if (skillCategory == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (skillCategory.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond skillCategory.errors, view:'edit'
			return
		}

		skillCategory.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'skillCategory.label', default: 'SkillCategory'), skillCategory.id])
				redirect action:"index", method:"GET"
			}
		}
	}

	@Transactional
	def delete(SkillCategory skillCategory) {

		if (skillCategory == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		skillCategory.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'skillCategory.label', default: 'SkillCategory'), skillCategory.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'skillCategory.label', default: 'SkillCategory'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
