package com.bromakgame

import com.bromakgame.creatures.Group

class Champion extends Creature {

	User user
	
	String firstName
	String lastName
	
	Set<Group> groups = new HashSet<>()
	
	String getFullName() {
		String fullName = firstName.trim()
		
		if (lastName?.trim()) {
			fullName = fullName + " " + lastName.trim()
		}
		
		return fullName
	}
	
    static constraints = {
		firstName blank: false
		lastName nullable: true
    }
}
