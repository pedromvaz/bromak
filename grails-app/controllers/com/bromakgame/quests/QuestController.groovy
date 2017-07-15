package com.bromakgame.quests

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import com.bromakgame.GenericController

@Secured('ROLE_PLAYER')
class QuestController extends GenericController {

	String getDomainClassName() {
		return "Quest"
	}

	@Secured('ROLE_UNKNOWN')
	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Quest.list(params), model:[questCount: Quest.count()]
	}

	@Secured('ROLE_UNKNOWN')
	def show(Quest quest) {
		super.show(quest)
	}

	@Secured('ROLE_UNKNOWN')
	def create() {
		respond new Quest(params)
	}

	def save(Quest quest) {
		super.save(quest)
	}

	void completeBeforeSaving(Object quest) {
	}

	@Secured('ROLE_UNKNOWN')
	def edit(Quest quest) {
		respond quest
	}

	@Secured('ROLE_UNKNOWN')
	def update(Quest quest) {
		super.update(quest)
	}

	@Secured('ROLE_UNKNOWN')
	def delete(Quest quest) {
		super.delete(quest)
	}

	def prepare(Quest quest) {
		respond quest, model: [ members: quest.owningGroup?.creatures, objectives: quest.type.objectives ]
	}
}
