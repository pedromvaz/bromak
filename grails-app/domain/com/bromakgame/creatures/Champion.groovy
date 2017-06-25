package com.bromakgame.creatures

import com.bromakgame.users.User

class Champion extends Creature {

	User user

	boolean isNameInUse(String name) {
		return Champion.findByFirstName(name) != null
	}

    static constraints = {
    }
}
