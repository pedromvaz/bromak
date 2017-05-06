package com.bromakgame.learning

import com.bromakgame.creatures.Race

class Skill {

	String name
	String description

	Set<Race> races = new HashSet<>()

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
