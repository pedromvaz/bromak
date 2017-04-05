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
        def userRole = new Role(authority: 'ROLE_USER', description: 'A player on the bromak website').save()

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
		
		def humanRace = new Race(name: 'Human', description: 'An arrogant race.', enabled: true).save()
		def dwarfRace = new Race(name: 'Dwarf', description: 'A greedy race.', enabled: true).save()
		def elfRace = new Race(name: 'Elf', description: 'A peaceful race.', enabled: true).save()
		def orcRace = new Race(name: 'Orc', description: 'A proud race.', enabled: true).save()
		def goblinRace = new Race(name: 'Goblin', description: 'A coward race.', enabled: true).save()
		def trollRace = new Race(name: 'Troll', description: 'A brutish race.', enabled: true).save()
		
    }
    
    def destroy = {
    }
}
