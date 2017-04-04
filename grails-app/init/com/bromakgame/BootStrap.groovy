package com.bromakgame

import com.bromakgame.Role
import com.bromakgame.User
import com.bromakgame.UserRole

class BootStrap {

    def init = {
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
    }
    
    def destroy = {
    }
}
