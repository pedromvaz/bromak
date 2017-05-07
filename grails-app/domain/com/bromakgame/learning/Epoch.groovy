package com.bromakgame.learning

class Epoch {

	String name
	String description

	static hasMany = [ skills : Skill ]

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
