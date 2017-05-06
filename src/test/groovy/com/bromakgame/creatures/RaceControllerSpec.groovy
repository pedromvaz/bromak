package com.bromakgame.creatures

import grails.test.mixin.*
import spock.lang.*

@TestFor(RaceController)
@Mock(Race)
class RaceControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        params["name"] = 'Human'
		params["description"] = 'An arrogante race'
		params["startingPopulation"] = 10
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.raceList
            model.raceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.race!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def race = new Race()
            race.validate()
            controller.save(race)

        then:"The create view is rendered again with the correct model"
            model.race!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            race = new Race(params)

            controller.save(race)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/race/index'
            controller.flash.message != null
            Race.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def race = new Race(params)
            controller.show(race)

        then:"A model is populated containing the domain instance"
            model.race == race
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def race = new Race(params)
            controller.edit(race)

        then:"A model is populated containing the domain instance"
            model.race == race
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/race/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def race = new Race()
            race.validate()
            controller.update(race)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.race == race

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            race = new Race(params).save(flush: true)
            controller.update(race)

        then:"A redirect is issued to the show action"
            race != null
            response.redirectedUrl == "/race/show/$race.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/race/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def race = new Race(params).save(flush: true)

        then:"It exists"
            Race.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(race)

        then:"The instance is deleted"
            Race.count() == 0
            response.redirectedUrl == '/race/index'
            flash.message != null
    }
}