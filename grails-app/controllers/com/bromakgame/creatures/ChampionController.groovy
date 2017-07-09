package com.bromakgame.creatures

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import grails.plugin.springsecurity.annotation.Secured
import com.bromakgame.creatures.Community
import com.bromakgame.learning.SkillLevel
import com.bromakgame.learning.Skill
import com.bromakgame.users.User
import com.bromakgame.worlds.World
import com.bromakgame.worlds.Tutorials

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
		
		// TODO: IF below can't stay here, we must allow multiple Champions per player, later on
		// Must find another way, like this http://docs.grails.org/latest/guide/theWebLayer.html#formtokens
		
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
			user.addToChampions(champion)
			user.save()
			
			World world = World.get(session.worldId)
			world.addToCreatures(champion)
			world.save()
			
			champion.validate()
			
			startCommunity(champion)
		}
		
		if (champion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond champion.errors, view:'create'
            return
        }
		
		// champion starts with basic skills knowledge
		SkillLevel bashingLevel = new SkillLevel(level: 1.0, skill : Skill.findByName('Bashing'))
		SkillLevel throwingLevel = new SkillLevel(level: 1.0, skill : Skill.findByName('Throwing'))
		SkillLevel stealthLevel = new SkillLevel(level: 1.0, skill : Skill.findByName('Stealth'))
		champion.addToLearnedSkills(bashingLevel)
		champion.addToLearnedSkills(throwingLevel)
		champion.addToLearnedSkills(stealthLevel)

        champion.save(flush:true)
		
        request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'champions.created.message',
					args: [champion.firstName])
				//redirect action: 'chooseSkills', id: champion.id
				redirect controller: 'world', action: 'show', id: champion.world.id
            }
        }
    }
	
	@Secured('ROLE_UNKNOWN')
	def chooseSkills(Champion champion) {
		respond champion, model: [ skills : champion.race.skillTree.skills ]
	}
	
	@Transactional
	def saveSkills(Champion champion) {
		if (champion == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

		// cycle through each selected Skill on the chooseSkills view
		// and assign it to the Champion
		params.each {
			if (it.value.equals("on")) {
				SkillLevel skillLevel = new SkillLevel(level: 1.0, skill : Skill.get(it.key))
				champion.addToLearnedSkills(skillLevel)
			}
		}

		if (champion.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond champion.errors, view:'create'
            return
        }

        champion.save(flush:true)

		request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'champions.created.message',
					args: [champion.firstName])
				redirect controller: 'world', action: 'show', id: champion.world.id
            }
        }
	}
	
	boolean playerHasChampionsAlive() {
		User player = springSecurityService?.getCurrentUser()
		
		return Champion.findAllWhere(user: player, alive: true).size() > 0
	}
	
	void startCommunity(Champion champion) {
		def race = champion.race
		def world = champion.world
		int startingPop = race.startingPopulation
		
		def community = new Community().save()
		
		for (int i = 0; i < startingPop; i += 2) {
			def maleCreature = new Creature(firstName: Creature.createRandomWord(),
				race: race, gender: 'm',
				world: world).save()
			def femaleCreature = new Creature(firstName: Creature.createRandomWord(),
				race: race, gender: 'f',
				world: world).save()
			
			community.addToCreatures(maleCreature)
			community.addToCreatures(femaleCreature)
		}
		
		community.addToCreatures(champion)
		community.save()
		
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
					args: [message(code: 'champions.label'), champion.id])
                redirect champion
            }
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
					args: [message(code: 'champions.label'), champion.id])
                redirect action:"index", method:"GET"
            }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(
					code: 'default.not.found.message',
					args: [message(code: 'champions.label'), params.id])
                redirect action: "index", method: "GET"
            }
        }
    }
}
