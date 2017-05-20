package com.bromakgame.creatures

import grails.test.mixin.*
import spock.lang.*
import com.bromakgame.creatures.Race
import com.bromakgame.users.User

@TestFor(ChampionController)
@Mock([Champion, Race, User, Community])
class ChampionControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["firstName"] = 'Aragorn'
		params["gender"] = 'm'
		params["race"] = new Race(name: 'Human')
		params["user"] = new User(username: 'User', email: 'user@email.com', password: 'pass')
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.championList
            model.championCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.champion!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def champion = new Champion()
            champion.validate()
            controller.save(champion)

        then:"The create view is rendered again with the correct model"
            model.champion!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            champion = new Champion(params)

            controller.save(champion)

        then:"A redirect is issued to the champion list"
            response.redirectedUrl == '/champion/index'
            controller.flash.message != null
            Champion.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def champion = new Champion(params)
            controller.show(champion)

        then:"A model is populated containing the domain instance"
            model.champion == champion
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def champion = new Champion(params)
            controller.edit(champion)

        then:"A model is populated containing the domain instance"
            model.champion == champion
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/champion/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def champion = new Champion()
            champion.validate()
            controller.update(champion)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.champion == champion

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            champion = new Champion(params).save(flush: true)
            controller.update(champion)

        then:"A redirect is issued to the show action"
            champion != null
            response.redirectedUrl == "/champion/show/$champion.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/champion/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def champion = new Champion(params).save(flush: true)

        then:"It exists"
            Champion.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(champion)

        then:"The instance is deleted"
            Champion.count() == 0
            response.redirectedUrl == '/champion/index'
            flash.message != null
    }
}
