package com.bromakgame.learning

class Skill {

	String name
	String description
	Epoch epoch
	SkillCategory category

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
		epoch nullable: true
		category nullable: true
	}
}
