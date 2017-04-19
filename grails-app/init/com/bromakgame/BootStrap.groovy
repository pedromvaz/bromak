package com.bromakgame

import com.bromakgame.Role
import com.bromakgame.User
import com.bromakgame.UserRole

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
		
		def champFather = new Champion(firstName: 'Father', gender: 'm', race: humanRace, user: testPlayer).save()
		def champMother = new Champion(firstName: 'Mother', gender: 'f', race: humanRace, user: testPlayer).save()
		def champSon = new Champion(firstName: 'Son', gender: 'm', race: humanRace, user: testPlayer).save()
		def champDaughter = new Champion(firstName: 'Daughter', gender: 'f', race: humanRace, user: testPlayer).save()
		
		assert Champion.count() == 4
		
		champSon.father = champFather
		champSon.mother = champMother
		
		champDaughter.father = champFather
		champDaughter.mother = champMother
    }
    
    def destroy = {
    }
}
