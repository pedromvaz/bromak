package com.bromakgame

import com.bromakgame.creatures.Group

class Champion extends Creature {

	User user
	
	String firstName
	String lastName
	String title
	
	Set<Group> groups = new HashSet<>()
	
	String getFullName() {
		String fullName = firstName
		
		if (title?.trim()) {
			fullName = title + " " + fullName
		}
		
		if (lastName?.trim()) {
			fullName = fullName + " " + lastName
		}
		
		return fullName
	}
	
    static constraints = {
		firstName blank: false
		lastName nullable: true
		title nullable: true
    }
}
