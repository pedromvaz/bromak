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
        def userRole = new Role(authority: 'ROLE_PLAYER', description: 'A player on the bromak website').save()

        def testUser = new User(username: 'admin', email: 'admin@bromakgame.com', password: 'admin', enabled: true).save()

        UserRole.create testUser, adminRole

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
			intelligent:true, enabled: true).save()
		def dwarfRace = new Race(name: 'Dwarf', description: 'A greedy race.',
			intelligent:true, enabled: true).save()
		def elfRace = new Race(name: 'Elf', description: 'A peaceful race.',
			intelligent:true, enabled: true).save()
		def orcRace = new Race(name: 'Orc', description: 'A proud race.',
			intelligent:true, enabled: true).save()
		def goblinRace = new Race(name: 'Goblin', description: 'A coward race.',
			intelligent:true, enabled: true).save()
		def trollRace = new Race(name: 'Troll', description: 'A brutish race.',
			intelligent:true, enabled: false).save()
		
		// savage ones
		def wolfRace = new Race(name: 'Wolf', description: 'A race that works in packs.',
			intelligent: false, enabled: true).save()
		def bearRace = new Race(name: 'Bear', description: 'A growling race.',
			intelligent: false, enabled: true).save()
		def eagleRace = new Race(name: 'Eagle', description: 'A flying race.',
			intelligent: false, enabled: true).save()
		
		assert Race.count() == 9
    }
    
    def destroy = {
    }
}
