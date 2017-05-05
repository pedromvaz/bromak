package com.bromakgame.learning

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN','ROLE_PLAYER'])
@Transactional(readOnly = true)
class EpochController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Epoch.list(params), model:[epochCount: Epoch.count()]
    }

    def show(Epoch epoch) {
        respond epoch
    }

    def create() {
        respond new Epoch(params)
    }

    @Transactional
    def save(Epoch epoch) {
        if (epoch == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (epoch.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond epoch.errors, view:'create'
            return
        }

        epoch.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'epoch.label', default: 'Epoch'), epoch.id])
                //redirect epoch
				redirect action:"index", method:"GET"
            }
            '*' { respond epoch, [status: CREATED] }
        }
    }

    def edit(Epoch epoch) {
        respond epoch
    }

    @Transactional
    def update(Epoch epoch) {
        if (epoch == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (epoch.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond epoch.errors, view:'edit'
            return
        }

        epoch.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'epoch.label', default: 'Epoch'), epoch.id])
                redirect epoch
            }
            '*'{ respond epoch, [status: OK] }
        }
    }

    @Transactional
    def delete(Epoch epoch) {

        if (epoch == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        epoch.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'epoch.label', default: 'Epoch'), epoch.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'epoch.label', default: 'Epoch'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
