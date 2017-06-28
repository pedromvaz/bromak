package com.bromakgame.worlds

import grails.test.mixin.*
import spock.lang.*

@TestFor(TutorialsController)
@Mock(Tutorials)
class TutorialsControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null

		params["name"] = 'bromak'
		params["radius"] = 2
		params["maxNumPlayers"] = 10
		
		params["owner.username"] = 'pedro'
		params["owner.email"] = 'pedro@bromakgame.com'
		params["owner.password"] = "pedro"
		params["owner.enabled"] = true
		params["owner.accountExpired"] = false
		params["owner.accountLocked"] = false
		params["owner.passwordExpired"] = false
	}

	void "Test the index action returns the correct model"() {

		when:"The index action is executed"
			controller.index()

		then:"The model is correct"
			!model.tutorialsList
			model.tutorialsCount == 0
	}

	void "Test the create action returns the correct model"() {
		when:"The create action is executed"
			controller.create()

		then:"The model is correctly created"
			model.tutorials!= null
	}

	@Ignore
	void "Test the save action correctly persists an instance"() {
		when:"The save action is executed with an invalid instance"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'POST'
			def tutorials = new Tutorials()
			tutorials.validate()
			controller.save(tutorials)

		then:"The create view is rendered again with the correct model"
			model.tutorials!= null
			view == 'create'

		when:"The save action is executed with a valid instance"
			response.reset()
			populateValidParams(params)
			tutorials = new Tutorials(params)

			controller.save(tutorials)

		then:"A redirect is issued to the show action"
			response.redirectedUrl == '/tutorials/show/1'
			controller.flash.message != null
			Tutorials.count() == 1
	}

	@Ignore
	void "Test that the show action returns the correct model"() {
		when:"The show action is executed with a null domain"
			controller.show(null)

		then:"A 404 error is returned"
			response.status == 404

		when:"A domain instance is passed to the show action"
			populateValidParams(params)
			def tutorials = new Tutorials(params)
			controller.show(tutorials)

		then:"A model is populated containing the domain instance"
			model.tutorials == tutorials
	}

	void "Test that the edit action returns the correct model"() {
		when:"The edit action is executed with a null domain"
			controller.edit(null)

		then:"A 404 error is returned"
			response.status == 404

		when:"A domain instance is passed to the edit action"
			populateValidParams(params)
			def tutorials = new Tutorials(params)
			controller.edit(tutorials)

		then:"A model is populated containing the domain instance"
			model.tutorials == tutorials
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when:"Update is called for a domain instance that doesn't exist"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'PUT'
			controller.update(null)

		then:"A 404 error is returned"
			response.redirectedUrl == '/tutorials/index'
			flash.message != null

		when:"An invalid domain instance is passed to the update action"
			response.reset()
			def tutorials = new Tutorials()
			tutorials.validate()
			controller.update(tutorials)

		then:"The edit view is rendered again with the invalid instance"
			view == 'edit'
			model.tutorials == tutorials

		when:"A valid domain instance is passed to the update action"
			response.reset()
			populateValidParams(params)
			tutorials = new Tutorials(params).save(flush: true)
			controller.update(tutorials)

		then:"A redirect is issued to the show action"
			tutorials != null
			response.redirectedUrl == "/tutorials/show/$tutorials.id"
			flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when:"The delete action is called for a null instance"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'DELETE'
			controller.delete(null)

		then:"A 404 is returned"
			response.redirectedUrl == '/tutorials/index'
			flash.message != null

		when:"A domain instance is created"
			response.reset()
			populateValidParams(params)
			def tutorials = new Tutorials(params).save(flush: true)

		then:"It exists"
			Tutorials.count() == 1

		when:"The domain instance is passed to the delete action"
			controller.delete(tutorials)

		then:"The instance is deleted"
			Tutorials.count() == 0
			response.redirectedUrl == '/tutorials/index'
			flash.message != null
	}
}
