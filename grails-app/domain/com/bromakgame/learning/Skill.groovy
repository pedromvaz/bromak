package com.bromakgame.learning

class Skill {

	String name
	String description

	static belongsTo = [ epoch : Epoch ]

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
