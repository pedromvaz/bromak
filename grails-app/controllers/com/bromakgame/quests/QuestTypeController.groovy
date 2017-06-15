package com.bromakgame.quests

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class QuestTypeController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond QuestType.list(params), model:[questTypeCount: QuestType.count()]
	}

	def show(QuestType questType) {
		respond questType
	}

	def create() {
		respond new QuestType(params)
	}

	@Transactional
	def save(QuestType questType) {
		if (questType == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (questType.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond questType.errors, view:'create'
			return
		}

		questType.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.created.message', args: [message(code: 'questType.label', default: 'QuestType'), questType.id])
				redirect action:"index", method:"GET"
			}
			'*' { respond questType, [status: CREATED] }
		}
	}

	def edit(QuestType questType) {
		respond questType
	}

	@Transactional
	def update(QuestType questType) {
		if (questType == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (questType.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond questType.errors, view:'edit'
			return
		}

		questType.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'questType.label', default: 'QuestType'), questType.id])
				redirect action:"index", method:"GET"
			}
			'*'{ respond questType, [status: OK] }
		}
	}

	@Transactional
	def delete(QuestType questType) {

		if (questType == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		questType.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'questType.label', default: 'QuestType'), questType.id])
				redirect action:"index", method:"GET"
			}
			'*'{ render status: NO_CONTENT }
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'questType.label', default: 'QuestType'), params.id])
				redirect action: "index", method: "GET"
			}
			'*'{ render status: NOT_FOUND }
		}
	}
}
