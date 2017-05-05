package com.bromakgame.learning

import grails.test.mixin.*
import spock.lang.*

@TestFor(TechnologyController)
@Mock(Technology)
class TechnologyControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["name"] = 'Name'
		params["description"] = 'Description'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.technologyList
            model.technologyCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.technology!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def technology = new Technology()
            technology.validate()
            controller.save(technology)

        then:"The create view is rendered again with the correct model"
            model.technology!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            technology = new Technology(params)

            controller.save(technology)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/epoch/index'
            controller.flash.message != null
            Technology.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def technology = new Technology(params)
            controller.show(technology)

        then:"A model is populated containing the domain instance"
            model.technology == technology
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def technology = new Technology(params)
            controller.edit(technology)

        then:"A model is populated containing the domain instance"
            model.technology == technology
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/technology/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def technology = new Technology()
            technology.validate()
            controller.update(technology)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.technology == technology

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            technology = new Technology(params).save(flush: true)
            controller.update(technology)

        then:"A redirect is issued to the show action"
            technology != null
            response.redirectedUrl == "/technology/show/$technology.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/technology/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def technology = new Technology(params).save(flush: true)

        then:"It exists"
            Technology.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(technology)

        then:"The instance is deleted"
            Technology.count() == 0
            response.redirectedUrl == '/technology/index'
            flash.message != null
    }
}
