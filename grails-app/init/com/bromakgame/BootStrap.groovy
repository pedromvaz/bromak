package com.bromakgame

import com.bromakgame.Role
import com.bromakgame.User
import com.bromakgame.UserRole

class BootStrap {

    def init = {
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def testUser = new User(username: 'admin', email: 'admin@bromakgame.com', password: 'admin').save()

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
