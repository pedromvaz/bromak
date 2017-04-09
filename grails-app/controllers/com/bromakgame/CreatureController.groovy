package com.bromakgame

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_UNKNOWN')
@Transactional(readOnly = true)
class CreatureController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Creature.list(params), model:[creatureCount: Creature.count()]
    }

    def show(Creature creature) {
        respond creature
    }

    def create() {
        respond new Creature(params)
    }

    @Transactional
    def save(Creature creature) {
        if (creature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (creature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond creature.errors, view:'create'
            return
        }

        creature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'creature.label', default: 'Creature'), creature.id])
                redirect creature
            }
            '*' { respond creature, [status: CREATED] }
        }
    }

    def edit(Creature creature) {
        respond creature
    }

    @Transactional
    def update(Creature creature) {
        if (creature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (creature.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond creature.errors, view:'edit'
            return
        }

        creature.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'creature.label', default: 'Creature'), creature.id])
                redirect creature
            }
            '*'{ respond creature, [status: OK] }
        }
    }

    @Transactional
    def delete(Creature creature) {

        if (creature == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        creature.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'creature.label', default: 'Creature'), creature.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'creature.label', default: 'Creature'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
