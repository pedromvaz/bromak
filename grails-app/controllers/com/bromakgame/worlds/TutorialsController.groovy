package com.bromakgame.worlds

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.bromakgame.users.User
import com.bromakgame.creatures.Champion
import com.bromakgame.creatures.Group
import com.bromakgame.quests.QuestType

@Secured('ROLE_PLAYER')
@Transactional(readOnly = true)
class TutorialsController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	transient springSecurityService

	@Secured('ROLE_UNKNOWN')
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Tutorials.list(params), model:[tutorialsCount: Tutorials.count()]
	}

	def show() {
		User player = springSecurityService?.getCurrentUser()
		Tutorials world = Tutorials.findByOwner(player)

		if (!world) {
			world = new Tutorials(name: message(code: 'tutorials.world.name'),
				radius: 0, maxNumPlayers: 1, owner: player)

			//world.addToQuestTypes(QuestType.findByName('Terrain Scouting'))
			//world.addToQuestTypes(QuestType.findByName('Animal Hunting'))

			if (!save(world)) {
				respond tutorials.errors, controller: 'world', view: 'choose'
				return
			}
		}

		// set the session variable when entering the world
		session["worldId"] = world.id

		Map model = [champions : Champion.findAllByWorld(world)]

		respond world, model : model
	}

	def create() {
		respond new Tutorials(params)
	}

	@Transactional
	private boolean save(Tutorials tutorials) {
		if (tutorials == null || tutorials.hasErrors()) {
			transactionStatus.setRollbackOnly()
			return false
		}

		tutorials.save flush:true

		return true
	}

	@Secured('ROLE_UNKNOWN')
	def edit(Tutorials tutorials) {
		respond tutorials
	}

	@Secured('ROLE_UNKNOWN')
	@Transactional
	def update(Tutorials tutorials) {
		if (tutorials == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		if (tutorials.hasErrors()) {
			transactionStatus.setRollbackOnly()
			respond tutorials.errors, view:'edit'
			return
		}

		tutorials.save flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.updated.message', args: [message(code: 'tutorials.label', default: 'Tutorials'), tutorials.id])
				redirect tutorials
			}
		}
	}

	@Secured('ROLE_UNKNOWN')
	@Transactional
	def delete(Tutorials tutorials) {

		if (tutorials == null) {
			transactionStatus.setRollbackOnly()
			notFound()
			return
		}

		tutorials.delete flush:true

		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.deleted.message', args: [message(code: 'tutorials.label', default: 'Tutorials'), tutorials.id])
				redirect action:"index", method:"GET"
			}
		}
	}

	protected void notFound() {
		request.withFormat {
			form multipartForm {
				flash.message = message(code: 'default.not.found.message', args: [message(code: 'tutorials.label', default: 'Tutorials'), params.id])
				redirect action: "index", method: "GET"
			}
		}
	}

	def champions() {
		User player = springSecurityService?.getCurrentUser()
		respond Tutorials.findByOwner(player)
	}

	def community() {
		User player = springSecurityService?.getCurrentUser()
		respond Tutorials.findByOwner(player)
	}

	def areas() {
		User player = springSecurityService?.getCurrentUser()
		respond Tutorials.findByOwner(player)
	}

	def quests() {
		User player = springSecurityService?.getCurrentUser()
		Tutorials world = Tutorials.findByOwner(player)

		def quests = []

		for (champion in player.champions) {
			if (champion.world != world) continue

			for (quest in champion.group.quests) {
				quests << quest
			}
		}

		respond world, model: [ quests : quests ]
	}
}
