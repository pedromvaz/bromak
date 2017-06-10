package com.bromakgame

import com.bromakgame.users.Role
import com.bromakgame.users.User
import com.bromakgame.users.UserRole
import com.bromakgame.worlds.World
import com.bromakgame.worlds.Region
import com.bromakgame.worlds.Area
import com.bromakgame.creatures.Community
import com.bromakgame.creatures.Race
import com.bromakgame.learning.Epoch
import com.bromakgame.learning.Skill

import com.bromakgame.creatures.Champion

class BootStrap {

    def init = {
		
		// -----------------------
		// Roles and Administrator
		// -----------------------
		
        def adminRole = new Role(authority: 'ROLE_ADMIN', description: 'An administrator of the bromak website').save()
        def adminUser = new User(username: 'admin', email: 'admin@bromakgame.com', password: 'admin', enabled: true).save()

        UserRole.create adminUser, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 1
        assert UserRole.count() == 1
		
		// --------------
		// Creature Races
		// --------------
		
		// intelligent ones
		def humanRace = new Race(name: 'Human', description: 'An arrogant race.',
			intelligent:true, enabled: true, startingPopulation: 20).save()
		def dwarfRace = new Race(name: 'Dwarf', description: 'A greedy race.',
			intelligent:true, enabled: true, startingPopulation: 15).save()
		def elfRace = new Race(name: 'Elf', description: 'A peaceful race.',
			intelligent:true, enabled: true, startingPopulation: 10).save()
		def orcRace = new Race(name: 'Orc', description: 'A proud race.',
			intelligent:true, enabled: true, startingPopulation: 25).save()
		def goblinRace = new Race(name: 'Goblin', description: 'A coward race.',
			intelligent:true, enabled: true, startingPopulation: 30).save()
		def trollRace = new Race(name: 'Troll', description: 'A brutish race.',
			intelligent:true, enabled: false, startingPopulation: 5).save()
		
		// savage ones
		def wolfRace = new Race(name: 'Wolf', description: 'A race that works in packs.',
			intelligent: false, enabled: true, startingPopulation: 15).save()
		def bearRace = new Race(name: 'Bear', description: 'A growling race.',
			intelligent: false, enabled: true, startingPopulation: 10).save()
		def eagleRace = new Race(name: 'Eagle', description: 'A flying race.',
			intelligent: false, enabled: true, startingPopulation: 5).save()
		
		assert Race.count() == 9
		
		// ------
		// Player
		// ------

		def playerRole = new Role(authority: 'ROLE_PLAYER', description: 'A player on the bromak website').save()
		def playerUser = new User(username: 'player', email: 'player@bromakgame.com', password: 'player', enabled: true).save()

        UserRole.create playerUser, playerRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 2
        assert Role.count() == 2
        assert UserRole.count() == 2
		
		// -------------------------
		// Champions and Family Tree
		// -------------------------
		
		def elrond = new Champion(firstName: 'Elrond', gender: 'm', race: elfRace, user: playerUser).save()
		def celebrian = new Champion(firstName: 'Celebrian', gender: 'f', race: elfRace, user: playerUser).save()
		def elladan = new Champion(firstName: 'Elladan', gender: 'm', race: elfRace, user: playerUser).save()
		def elrohir = new Champion(firstName: 'Elrohir', gender: 'm', race: elfRace, user: playerUser).save()
		def arwen = new Champion(firstName: 'Arwen', gender: 'f', race: elfRace, user: playerUser).save()
		
		assert Champion.count() == 5
		
		for (child in [elladan, elrohir, arwen]) {
			child.father = elrond
			child.mother = celebrian
		}
		
		// -----------
		// Communities
		// -----------
		
		def community = new Community().save()
		
		for (champion in [elrond, celebrian, elladan, elrohir, arwen]) {
			champion.groups.add(community)
			community.add(champion)
		}
		
		// -----------------
		// Epochs and Skills
		// -----------------
		
		// hunting
		def tracking = new Skill(
			name: 'Tracking',
			description: 'Tracking animals by analysing footprints, blood stains and smells')
		def throwing = new Skill(name: 'Throwing', description: 'Throwing weapons like stones and spears')
		def stealth = new Skill(name: 'Stealth', description: 'Moving silently')
		// stone carving
		def stoneCarving = new Skill(name: 'Stone Carving', description: 'The carving of stone weapons and tools.')
		// basic clothing
		def leatherworking = new Skill(
			name: 'Leatherworking',
			description: 'Skinning the leather from animals to make basic clothing.')
		// cave painting
		def cavePainting = new Skill(name: 'Cave Painting', description: 'Painting deeds in cave walls.')
		
		def nomadic = new Epoch(
			name: 'Ancient Nomadic Era',
			description: 'The start of the Ancient Era, where Nomadic peoples were common.')
		.addToSkills(tracking)
		.addToSkills(throwing)
		.addToSkills(stealth)
		.addToSkills(stoneCarving)
		.addToSkills(leatherworking)
		.addToSkills(cavePainting)
		.save()
		
		for (race in Race.findAllByIntelligent(true)) {
			for (skill in nomadic.skills) {
				race.addToLearnableSkills(skill)
			}
			race.save()
		}
		
		def agriculture = new Skill(name: 'Agriculture', description: 'Agriculture')
		def masonry = new Skill(name: 'Masonry', description: 'Masonry')
		def pottery = new Skill(name: 'Pottery', description: 'Pottery')
		
		def sedentary = new Epoch(
			name: 'Ancient Sedentary Era',
			description: 'The end of the Ancient Era, where Nomadic peoples became Sedentary.')
		.addToSkills(agriculture)
		.addToSkills(masonry)
		.addToSkills(pottery)
		.save()
		
		// ---------------
		// World & Regions
		// ---------------
		
		def worldRadius = 2
		def world = new World(name: "Bromak", radius: worldRadius, maxNumPlayers: 10)
		world.owner = adminUser
		
		world.generate()
		
		world.save(flush:true)
		
		assert World.count() == 1
		assert Region.count() == numRegionsByRadius(worldRadius)
		assert Area.count() == numRegionsByRadius(worldRadius) * 7
	}

	private int numRegionsByRadius(int radius) {
		if (radius == 0) {
			return 1
		} else {
			return radius * 6 + numRegionsByRadius(radius - 1)
		}
	}

	def destroy = {
	}
}
