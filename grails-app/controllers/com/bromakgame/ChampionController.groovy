package com.bromakgame

import static org.springframework.http.HttpStatus.*
import com.bromakgame.creatures.Community
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_PLAYER')
@Transactional(readOnly = true)
class ChampionController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
	
	transient springSecurityService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		
		User player = springSecurityService?.getCurrentUser()
		int champCount = Champion.findAllWhere(user: player).size()
		
		def model = [ championCount: champCount ]
		model << [ hasChampionsAlive: playerHasChampionsAlive() ]
		
		respond Champion.findAllWhere(user: player, params), model: model
    }

	@Secured('ROLE_UNKNOWN')
    def show(Champion champion) {
        respond champion
    }

    def create() {
		if (playerHasChampionsAlive()) {
			redirect action:"index", method:"GET"
			return
		}
		
		respond new Champion(params)
    }

    @Transactional
    def save(Champion champion) {
        if (champion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
		
		// in case the user creates a Champion, then clicks "Back" in the browser
		// and tries to create a second Champion
		if (playerHasChampionsAlive()) {
			transactionStatus.setRollbackOnly()
			redirect action:"index", method:"GET"
			return
		}

		// in order to associate User with Champion
		// it must be done before hasErrors()
		// and a new validate() must be run to validate User is now set
		// hasErrors() will use result from last validate()
		User user = springSecurityService?.getCurrentUser()
		
		if (user != null) {
			champion.user = user
			champion.validate()
		}

        if (champion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond champion.errors, view:'create'
            return
        }

        champion.save(flush:true)
		
		if (user != null) {
			user.champions.add(champion)
			user.save(flush:true)
			
			startCommunity(champion)
		}
		
        request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'champions.created.message',
					args: [champion.firstName])
				redirect action:"index", method:"GET"
            }
            '*' { respond champion, [status: CREATED] }
        }
    }
	
	boolean playerHasChampionsAlive() {
		User player = springSecurityService?.getCurrentUser()
		
		return Champion.findAllWhere(user: player, alive: true).size() > 0
	}
	
	void startCommunity(Champion champion) {
		def race = champion.race
		int startingPop = race.startingPopulation
		
		def community = new Community().save()
		
		for (int i = 0; i < startingPop; i += 2) {
			def maleCreature = new Creature(race: race, gender: 'm').save()
			def femaleCreature = new Creature(race: race, gender: 'f').save()
			
			community.add(maleCreature)
			community.add(femaleCreature)
		}
		
		community.add(champion)
		community.save()
		
		champion.groups.add(community)
		champion.save(flush:true)
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
