package com.bromakgame.worlds

import grails.test.mixin.*
import spock.lang.*

@TestFor(WorldController)
@Mock(World)
class WorldControllerSpec extends Specification {

	def populateValidParams(params) {
		assert params != null

		params["name"] = 'bromak'
		params["radius"] = 2
	}

	void "Test the index action returns the correct model"() {

		when:"The index action is executed"
			controller.index()

		then:"The model is correct"
			!model.worldList
			model.worldCount == 0
	}

	void "Test the create action returns the correct model"() {
		when:"The create action is executed"
			controller.create()

		then:"The model is correctly created"
			model.world!= null
	}

	void "Test the save action correctly persists an instance"() {

		when:"The save action is executed with an invalid instance"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'POST'
			def world = new World()
			world.validate()
			controller.save(world)

		then:"The create view is rendered again with the correct model"
			model.world!= null
			view == 'create'

		when:"The save action is executed with a valid instance"
			response.reset()
			populateValidParams(params)
			world = new World(params)

			controller.save(world)

		then:"A redirect is issued to the show action"
			response.redirectedUrl == '/world/index'
			controller.flash.message != null
			World.count() == 1
	}

	void "Test that the show action returns the correct model"() {
		when:"The show action is executed with a null domain"
			controller.show(null)

		then:"A 404 error is returned"
			response.status == 404

		when:"A domain instance is passed to the show action"
			populateValidParams(params)
			def world = new World(params)
			controller.show(world)

		then:"A model is populated containing the domain instance"
			model.world == world
	}

	void "Test that the edit action returns the correct model"() {
		when:"The edit action is executed with a null domain"
			controller.edit(null)

		then:"A 404 error is returned"
			response.status == 404

		when:"A domain instance is passed to the edit action"
			populateValidParams(params)
			def world = new World(params)
			controller.edit(world)

		then:"A model is populated containing the domain instance"
			model.world == world
	}

	void "Test the update action performs an update on a valid domain instance"() {
		when:"Update is called for a domain instance that doesn't exist"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'PUT'
			controller.update(null)

		then:"A 404 error is returned"
			response.redirectedUrl == '/world/index'
			flash.message != null

		when:"An invalid domain instance is passed to the update action"
			response.reset()
			def world = new World()
			world.validate()
			controller.update(world)

		then:"The edit view is rendered again with the invalid instance"
			view == 'edit'
			model.world == world

		when:"A valid domain instance is passed to the update action"
			response.reset()
			populateValidParams(params)
			world = new World(params).save(flush: true)
			controller.update(world)

		then:"A redirect is issued to the show action"
			world != null
			response.redirectedUrl == "/world/show/$world.id"
			flash.message != null
	}

	void "Test that the delete action deletes an instance if it exists"() {
		when:"The delete action is called for a null instance"
			request.contentType = FORM_CONTENT_TYPE
			request.method = 'DELETE'
			controller.delete(null)

		then:"A 404 is returned"
			response.redirectedUrl == '/world/index'
			flash.message != null

		when:"A domain instance is created"
			response.reset()
			populateValidParams(params)
			def world = new World(params).save(flush: true)

		then:"It exists"
			World.count() == 1

		when:"The domain instance is passed to the delete action"
			controller.delete(world)

		then:"The instance is deleted"
			World.count() == 0
			response.redirectedUrl == '/world/index'
			flash.message != null
	}
}
