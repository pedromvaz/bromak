package com.bromakgame.creatures

import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured
import com.bromakgame.GenericController
import com.bromakgame.learning.SkillTree

@Secured('ROLE_ADMIN')
class RaceController extends GenericController {

	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	String getDomainClassName() {
		return "Race"
	}

	def index(Integer max) {
		params.max = Math.min(max ?: 10, 100)
		respond Race.list(params), model:[raceCount: Race.count()]
	}

	def show(Race race) {
		super.show(race)
	}

	def create() {
		respond new Race(params)
	}

	def save(Race race) {
		super.save(race)
	}

	void completeBeforeSaving(Object race) {
		race.skillTree = new SkillTree(race: race)
	}

	@Secured('ROLE_UNKNOWN')
	def edit(Race race) {
		respond race
	}

	@Secured('ROLE_UNKNOWN')
	def update(Race race) {
		super.update(race)
	}

	@Secured('ROLE_UNKNOWN')
	def delete(Race race) {
		super.delete(race)
	}
}
