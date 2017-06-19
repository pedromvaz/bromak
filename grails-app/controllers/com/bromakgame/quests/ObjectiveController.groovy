package com.bromakgame.quests

import static org.springframework.http.HttpStatus.*
import com.bromakgame.learning.SkillCategory
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class ObjectiveController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Objective.list(params), model: [objectiveCount: Objective.count()]
	}

	def show(Objective objective) {
		respond objective
	}

	def create() {
		respond new Objective(params), model: [ skillCategories : SkillCategory.listOrderByName() ]
		
		if (session) {
			session["questTypeId"] = params.questTypeId
		}
	}

	@Transactional
	def save(Objective objective) {
		if (objective == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}
		
		if (session && session["questTypeId"]) {
			QuestType questType = QuestType.get(session["questTypeId"])

			questType.addToObjectives(objective)
			questType.save()
		}

		objective.validate()

		if (objective.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond objective.errors, view:'create'
			return
		}

		objective.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'objective.label', default: 'Objective'), objective.id])
				redirect objective
			}
			'*' { respond objective, [status: CREATED] }
		}
	}

	def edit(Objective objective) {
		respond objective, model: [ skillCategories : SkillCategory.listOrderByName() ]
	}

	@Transactional
	def update(Objective objective) {
		if (objective == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (objective.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond objective.errors, view:'edit'
			return
		}

		objective.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'objective.label', default: 'Objective'), objective.id])
				redirect objective
			}
			'*'{ respond objective, [status: OK] }
		}
	}

	@Transactional
	def delete(Objective objective) {

		if (objective == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		objective.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'objective.label', default: 'Objective'), objective.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'objective.label', default: 'Objective'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
