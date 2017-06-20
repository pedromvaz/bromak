package com.bromakgame.quests

import grails.test.mixin.*
import spock.lang.*
import com.bromakgame.learning.SkillCategory

@TestFor(ObjectiveController)
@Mock([Objective,QuestType,SkillCategory])
class ObjectiveControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null

		params["description"] = 'Execute a stealth approach'
		params["questType.name"] = 'Animal Hunting'
		params["questType.description"] = 'Hunting of wild animals'
		params["questType.groupCap"] = 4
		params["skillCategory.name"] = 'Combat'
		params["skillCategory.description"] = 'Various forms of combat'
	}

	void "Test the index action returns the correct model"() {

		when:"The index action is executed"
			controller.index()

		then:"The model is correct"
			!model.objectiveList
			model.objectiveCount == 0
	}

	void "Test the create action returns the correct model"() {
		when:"The create action is executed"
			controller.create()

		then:"The model is correctly created"
			model.objective!= null
	}

	void "Test the save action correctly persists an instance"() {

		when:"The save action is executed with an invalid instance"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'POST'
			def objective = new Objective()
			objective.validate()
			controller.save(objective)

		then:"The create view is rendered again with the correct model"
			model.objective!= null
			view == 'create'

		when:"The save action is executed with a valid instance"
			response.reset()
			populateValidParams(params)
			objective = new Objective(params)

			controller.save(objective)

		then:"A redirect is issued to the show action"
			response.redirectedUrl == '/questType/show'
			controller.flash.message != null
			Objective.count() == 1
	}

	void "Test that the show action returns the correct model"() {
		when:"The show action is executed with a null domain"
			controller.show(null)

		then:"A 404 error is returned"
			response.status == 404

		when:"A domain instance is passed to the show action"
			populateValidParams(params)
			def objective = new Objective(params)
			controller.show(objective)

		then:"A model is populated containing the domain instance"
			model.objective == objective
	}

	void "Test that the edit action returns the correct model"() {
		when:"The edit action is executed with a null domain"
			controller.edit(null)

		then:"A 404 error is returned"
			response.status == 404

		when:"A domain instance is passed to the edit action"
			populateValidParams(params)
			def objective = new Objective(params)
			controller.edit(objective)

		then:"A model is populated containing the domain instance"
			model.objective == objective
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when:"Update is called for a domain instance that doesn't exist"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'PUT'
			controller.update(null)

		then:"A 404 error is returned"
			response.redirectedUrl == '/objective/index'
			flash.message != null

		when:"An invalid domain instance is passed to the update action"
			response.reset()
			def objective = new Objective()
			objective.validate()
			controller.update(objective)

		then:"The edit view is rendered again with the invalid instance"
			view == 'edit'
			model.objective == objective

		when:"A valid domain instance is passed to the update action"
			response.reset()
			populateValidParams(params)
			objective = new Objective(params).save(flush: true)
			controller.update(objective)

		then:"A redirect is issued to the show action"
			objective != null
			response.redirectedUrl == "/objective/show/$objective.id"
			flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when:"The delete action is called for a null instance"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'DELETE'
			controller.delete(null)

		then:"A 404 is returned"
			response.redirectedUrl == '/objective/index'
			flash.message != null

		when:"A domain instance is created"
			response.reset()
			populateValidParams(params)
			def objective = new Objective(params).save(flush: true)

		then:"It exists"
			Objective.count() == 1

		when:"The domain instance is passed to the delete action"
			controller.delete(objective)

		then:"The instance is deleted"
			Objective.count() == 0
			response.redirectedUrl == '/objective/index'
			flash.message != null
	}
}
