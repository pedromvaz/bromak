package com.bromakgame.creatures

import com.bromakgame.users.User

class Champion extends Creature {

	User user

	Set<Group> groups = new HashSet<>()

	boolean isNameInUse(String name) {
		return Champion.findByFirstName(name) != null
	}

    static constraints = {
    }
}
