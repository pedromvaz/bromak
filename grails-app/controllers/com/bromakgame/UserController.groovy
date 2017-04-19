package com.bromakgame

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userCount: User.count()]
    }

	//@Secured(['ROLE_ADMIN', 'ROLE_PLAYER'])
	@Secured('ROLE_UNKNOWN')
    def show(User user) {
		//if (user != null && user != springSecurityService.currentUser)
		//	notFound()
		
        respond user
    }

	@Secured(value = ['IS_AUTHENTICATED_ANONYMOUSLY'], httpMethod = 'GET')
    def create() {
        respond new User(params)
    }

	@Secured(value = ['IS_AUTHENTICATED_ANONYMOUSLY'], httpMethod = 'POST')
    @Transactional
    def save(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'create'
            return
        }

        user.save flush:true
		
		def roleUser = Role.findByAuthority('ROLE_PLAYER')
		UserRole.create user, roleUser
		
		
		
		// if user registration, go to champion index page
		String roleBasedController = "champion"
		
		// if admin creating new user, go to user index page
		if (springSecurityService?.currentUser != null) {
			roleBasedController = "user"
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.username])
                //redirect user
				redirect controller:roleBasedController, action:"index", method:"GET"
            }
            '*' { respond user, [status: CREATED] }
        }
    }

	//@Secured(['ROLE_ADMIN', 'ROLE_PLAYER'])
	@Secured('ROLE_UNKNOWN')
    def edit(User user) {
        respond user
    }

	//@Secured(['ROLE_ADMIN', 'ROLE_PLAYER'])
	@Secured('ROLE_UNKNOWN')
    @Transactional
    def update(User user) {
        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'edit'
            return
        }

        user.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

	//@Secured(['ROLE_ADMIN', 'ROLE_PLAYER'])
	@Secured('ROLE_UNKNOWN')
    @Transactional
    def delete(User user) {

        if (user == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        user.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
