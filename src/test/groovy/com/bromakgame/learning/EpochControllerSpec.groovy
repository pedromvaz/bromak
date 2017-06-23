package com.bromakgame.learning

import grails.test.mixin.*
import spock.lang.*

@TestFor(EpochController)
@Mock(Epoch)
class EpochControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["name"] = 'Name'
		params["description"] = 'Description'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.epochList
            model.epochCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.epoch!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def epoch = new Epoch()
            epoch.validate()
            controller.save(epoch)

        then:"The create view is rendered again with the correct model"
            model.epoch!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            epoch = new Epoch(params)

            controller.save(epoch)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/epoch/index'
            controller.flash.message != null
            Epoch.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def epoch = new Epoch(params)
            controller.show(epoch)

        then:"A model is populated containing the domain instance"
            model.epoch == epoch
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def epoch = new Epoch(params)
            controller.edit(epoch)

        then:"A model is populated containing the domain instance"
            model.epoch == epoch
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/epoch/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def epoch = new Epoch()
            epoch.validate()
            controller.update(epoch)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.epoch == epoch

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            epoch = new Epoch(params).save(flush: true)
            controller.update(epoch)

        then:"A redirect is issued to the show action"
            epoch != null
            response.redirectedUrl == "/epoch/index"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/epoch/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def epoch = new Epoch(params).save(flush: true)

        then:"It exists"
            Epoch.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(epoch)

        then:"The instance is deleted"
            Epoch.count() == 0
            response.redirectedUrl == '/epoch/index'
            flash.message != null
    }
}
