package com.bromakgame.learning

class Skill {

	String name
	String description

	Set<Ability> requiredAbilities = new HashSet<>()

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
