package com.bromakgame

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class RaceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Race.list(params), model:[raceCount: Race.count()]
    }

	@Secured('ROLE_UNKNOWN')
    def show(Race race) {
        respond race
    }

    def create() {
        respond new Race(params)
    }

    @Transactional
    def save(Race race) {
        if (race == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (race.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond race.errors, view:'create'
            return
        }

        race.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'race.label', default: 'Race'), race.id])
                //redirect race
				redirect action:"index", method:"GET"
            }
            '*' { respond race, [status: CREATED] }
        }
    }

	@Secured('ROLE_UNKNOWN')
    def edit(Race race) {
        respond race
    }

	@Secured('ROLE_UNKNOWN')
    @Transactional
    def update(Race race) {
        if (race == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (race.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond race.errors, view:'edit'
            return
        }

        race.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'race.label', default: 'Race'), race.id])
                redirect race
            }
            '*'{ respond race, [status: OK] }
        }
    }

	@Secured('ROLE_UNKNOWN')
    @Transactional
    def delete(Race race) {

        if (race == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        race.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'race.label', default: 'Race'), race.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'race.label', default: 'Race'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
