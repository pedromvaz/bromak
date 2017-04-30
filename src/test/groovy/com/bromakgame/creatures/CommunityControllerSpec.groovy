package com.bromakgame.creatures

import grails.test.mixin.*
import spock.lang.*

@TestFor(CommunityController)
@Mock(Community)
class CommunityControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.communityList
            model.communityCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.community!= null
    }

    void "Test the save action correctly persists an instance"() {
		
        when:"The save action is executed with a valid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def community = new Community(params)
            community.validate()
            controller.save(community)
		
        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/community/show/1'
            controller.flash.message != null
            Community.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def community = new Community(params)
            controller.show(community)

        then:"A model is populated containing the domain instance"
            model.community == community
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def community = new Community(params)
            controller.edit(community)

        then:"A model is populated containing the domain instance"
            model.community == community
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/community/index'
            flash.message != null

		/*
        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def community = new Community()
            community.validate()
            controller.update(community)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.community == community
		*/

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            def community = new Community(params).save(flush: true)
            controller.update(community)

        then:"A redirect is issued to the show action"
            community != null
            response.redirectedUrl == "/community/show/$community.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/community/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def community = new Community(params).save(flush: true)

        then:"It exists"
            Community.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(community)

        then:"The instance is deleted"
            Community.count() == 0
            response.redirectedUrl == '/community/index'
            flash.message != null
    }
}
