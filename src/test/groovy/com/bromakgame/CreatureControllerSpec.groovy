package com.bromakgame

import grails.test.mixin.*
import spock.lang.*

@TestFor(CreatureController)
@Mock([Creature, Race])
class CreatureControllerSpec extends Specification {

	//def race = Mock(Race)
	
    def populateValidParams(params) {
        assert params != null
		
        params["firstName"] = 'Charming'
		params["title"] = 'Prince'
        params["gender"] = 'm'
		params["race"] = new Race(name: 'Human', description: 'Human')
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.creatureList
            model.creatureCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.creature!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def creature = new Creature()
            creature.validate()
            controller.save(creature)

        then:"The create view is rendered again with the correct model"
            model.creature!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            creature = new Creature(params)

            controller.save(creature)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/creature/show/1'
            controller.flash.message != null
            Creature.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def creature = new Creature(params)
            controller.show(creature)

        then:"A model is populated containing the domain instance"
            model.creature == creature
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def creature = new Creature(params)
            controller.edit(creature)

        then:"A model is populated containing the domain instance"
            model.creature == creature
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/creature/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def creature = new Creature()
            creature.validate()
            controller.update(creature)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.creature == creature

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            creature = new Creature(params).save(flush: true)
            controller.update(creature)

        then:"A redirect is issued to the show action"
            creature != null
            response.redirectedUrl == "/creature/show/$creature.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/creature/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def creature = new Creature(params).save(flush: true)

        then:"It exists"
            Creature.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(creature)

        then:"The instance is deleted"
            Creature.count() == 0
            response.redirectedUrl == '/creature/index'
            flash.message != null
    }
}
