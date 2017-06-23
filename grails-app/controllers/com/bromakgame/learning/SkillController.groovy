package com.bromakgame.learning

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class SkillController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Skill.list(params), model:[skillCount: Skill.count()]
	}

	@Secured('ROLE_UNKNOWN')
	def show(Skill skill) {
		respond skill
	}

	def create() {
		respond new Skill(params), model: [ categories: SkillCategory.listOrderByName(), epochs: Epoch.listOrderByName() ]
	}

	@Transactional
	def save(Skill skill) {
		if (skill == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (skill.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond skill.errors, view:'create'
			return
		}

		skill.save(flush:true)

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'skill.label', default: 'Skill'), skill.id])
				//redirect skill
				redirect action:"index", method:"GET"
			}
		}
	}

	def edit(Skill skill) {
		respond skill, model: [ categories: SkillCategory.listOrderByName(), epochs: Epoch.listOrderByName() ]
	}

	@Transactional
	def update(Skill skill) {
		if (skill == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (skill.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond skill.errors, view:'edit'
			return
		}

		skill.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'skill.label', default: 'Skill'), skill.id])
				redirect action:"index", method:"GET"
			}
		}
	}

	@Secured('ROLE_UNKNOWN')
	@Transactional
	def delete(Skill skill) {

		if (skill == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		skill.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'skill.label', default: 'Skill'), skill.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'skill.label', default: 'Skill'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
