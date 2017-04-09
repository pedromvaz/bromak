package com.bromakgame

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_PLAYER')
@Transactional(readOnly = true)
class ChampionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	transient springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Champion.list(params), model:[championCount: Champion.count()]
    }

	@Secured('ROLE_UNKNOWN')
    def show(Champion champion) {
        respond champion
    }

    def create() {
        respond new Champion(params)
    }

    @Transactional
    def save(Champion champion) {
        if (champion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (champion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond champion.errors, view:'create'
            return
        }

        champion.save flush:true
		
		User user = springSecurityService?.getCurrentUser()
		
		if (user != null) {
			user.addToChampions(champion).save()
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'champions.created.message',
					args: [champion.firstName])
                //redirect champion
				redirect action:"index", method:"GET"
            }
            '*' { respond champion, [status: CREATED] }
        }
    }

	@Secured('ROLE_UNKNOWN')
    def edit(Champion champion) {
        respond champion
    }

	@Secured('ROLE_UNKNOWN')
    @Transactional
    def update(Champion champion) {
        if (champion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (champion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond champion.errors, view:'edit'
            return
        }

        champion.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'default.updated.message',
					args: [message(code: 'champion.label', default: 'Champion'), champion.id])
                redirect champion
            }
            '*'{ respond champion, [status: OK] }
        }
    }

	@Secured('ROLE_UNKNOWN')
    @Transactional
    def delete(Champion champion) {

        if (champion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        champion.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'default.deleted.message',
					args: [message(code: 'champion.label', default: 'Champion'), champion.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'default.not.found.message',
					args: [message(code: 'champion.label', default: 'Champion'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
