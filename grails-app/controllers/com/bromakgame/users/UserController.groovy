package com.bromakgame.users

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import grails.plugin.springsecurity.SpringSecurityUtils

@Secured('ROLE_ADMIN')
@Transactional(readOnly = true)
class UserController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	def springSecurityService

	@Secured(['ROLE_ADMIN','ROLE_PLAYER'])
	def home() {
		if (SpringSecurityUtils.ifAllGranted('ROLE_ADMIN')) {
			redirect controller: 'user', action: 'index'
			return
		}
		
		if (SpringSecurityUtils.ifAllGranted('ROLE_PLAYER')) {
			redirect controller: 'world', action: 'choose'
			return
		}
	}
	
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

		// only admin can be logged in at this point
		// players don't see the enabled checkbox, so it must be true when an account is created
		// admins see the enabled checkbox, so it must be false if they don't tick the checkbox
		//boolean isPlayer = (springSecurityService?.currentUser == null)
		
		//user.enabled = params.enabled ?: isPlayer
		//user.validate()

        if (user.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond user.errors, view:'create'
            return
        }

        user.save flush:true
		
		def roleUser = Role.findByAuthority('ROLE_PLAYER')
		UserRole.create user, roleUser

		// only send activation email if not running a unit test,
		// and if user was not enabled by admin at creation time
		if (springSecurityService != null && !user.enabled) {
			sendActivationEmail(user)
		}

		// if admin creating new user, go to user index page
		if (springSecurityService?.currentUser != null) {
			request.withFormat {
				form multipartForm {
					flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.username])
					redirect controller:"user", action:"index", method:"GET"
				}
			}
		}
		// else this is a new player, so go to login page
		else {
			request.withFormat {
				form multipartForm {
					flash.message = message(code: 'users.accountActivationEmailSent.label')
					redirect controller:"login", action:"auth"
				}
			}
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

	private void sendActivationEmail(User user) {
		sendMail {
			to user.email
			subject "Welcome to Bromak!"
			html view: "/emails/activation", model: [username: user.username, encodedEmail: user.encodeActivationString()]
		}
	}

	@Secured('IS_AUTHENTICATED_ANONYMOUSLY')
	def activate() {
		User user = User.findByUsername(params.username)
		
		if (springSecurityService.passwordEncoder.isPasswordValid(params.hash, user.activationString(), null)) {
			user.enabled = true
			user.save flush:true
			
			request.withFormat {
				form multipartForm {
					flash.message = message(code: 'users.accountActivationSucceeded.label')
					redirect controller: "login", action: "auth"
				}
			}
		} else {
			request.withFormat {
				form multipartForm {
					flash.message = message(code: 'users.accountActivationFailed.label')
					redirect controller: "login", action: "auth"
				}
			}
		}
	}
}
