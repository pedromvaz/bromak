package com.bromakgame.creatures

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_PLAYER')
@Transactional(readOnly = true)
class CommunityController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	@Secured('ROLE_UNKNOWN')
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Community.list(params), model:[communityCount: Community.count()]
    }

    def show(Community community) {
        respond community
    }

	@Secured('ROLE_UNKNOWN')
    def create() {
        respond new Community(params)
    }

	@Secured('ROLE_UNKNOWN')
    @Transactional
    def save(Community community) {
        if (community == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (community.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond community.errors, view:'create'
            return
        }

        community.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'community.label', default: 'Community'), community.id])
                redirect community
            }
            '*' { respond community, [status: CREATED] }
        }
    }

	@Secured('ROLE_UNKNOWN')
    def edit(Community community) {
        respond community
    }

	@Secured('ROLE_UNKNOWN')
    @Transactional
    def update(Community community) {
        if (community == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (community.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond community.errors, view:'edit'
            return
        }

        community.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'community.label', default: 'Community'), community.id])
                redirect community
            }
            '*'{ respond community, [status: OK] }
        }
    }

	@Secured('ROLE_UNKNOWN')
    @Transactional
    def delete(Community community) {

        if (community == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        community.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'community.label', default: 'Community'), community.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'community.label', default: 'Community'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
