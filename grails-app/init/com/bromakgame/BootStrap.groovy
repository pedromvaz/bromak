package com.bromakgame

import com.bromakgame.Role
import com.bromakgame.User
import com.bromakgame.UserRole
import com.bromakgame.creatures.Community
import com.bromakgame.creatures.Race
import com.bromakgame.learning.Epoch
import com.bromakgame.learning.Technology

import com.bromakgame.creatures.Champion

class BootStrap {

    def init = {
		
		// -----------------------
		// Roles and Administrator
		// -----------------------
		
        def adminRole = new Role(authority: 'ROLE_ADMIN', description: 'An administrator of the bromak website').save()
        def playerRole = new Role(authority: 'ROLE_PLAYER', description: 'A player on the bromak website').save()

        def testAdmin = new User(username: 'admin', email: 'admin@bromakgame.com', password: 'admin', enabled: true).save()

        UserRole.create testAdmin, adminRole

        UserRole.withSession {
            it.flush()
            it.clear()
        }

        assert User.count() == 1
        assert Role.count() == 2
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
		
		def testPlayer = new User(username: 'player', email: 'player@bromakgame.com', password: 'player', enabled: true).save()

        UserRole.create testPlayer, playerRole

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
		
		def elrond = new Champion(firstName: 'Elrond', gender: 'm', race: elfRace, user: testPlayer).save()
		def celebrian = new Champion(firstName: 'Celebrian', gender: 'f', race: elfRace, user: testPlayer).save()
		def elladan = new Champion(firstName: 'Elladan', gender: 'm', race: elfRace, user: testPlayer).save()
		def elrohir = new Champion(firstName: 'Elrohir', gender: 'm', race: elfRace, user: testPlayer).save()
		def arwen = new Champion(firstName: 'Arwen', gender: 'f', race: elfRace, user: testPlayer).save()
		
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
		
		// -----------------------
		// Epochs and Technologies
		// -----------------------
		
		def hunting = new Technology(name: 'Hunting', description: 'Hunting').save()
		def gathering = new Technology(name: 'Gathering', description: 'Gathering').save()
		
		def nomadic = new Epoch(name: 'Ancient Nomadic Era', description: 'The start of the Ancient Era, where Nomadic peoples were common.')
		nomadic.add(hunting)
		nomadic.add(gathering)
		nomadic.save()
		
		def agriculture = new Technology(name: 'Agriculture', description: 'Agriculture').save()
		def masonry = new Technology(name: 'Masonry', description: 'Masonry').save()
		def pottery = new Technology(name: 'Pottery', description: 'Pottery').save()
		
		def sedentary = new Epoch(name: 'Ancient Sedentary Era', description: 'The end of the Ancient Era, where Nomadic peoples became Sedentary.')
		sedentary.add(agriculture)
		sedentary.add(masonry)
		sedentary.add(pottery)
		sedentary.save()
    }
    
    def destroy = {
    }
}
