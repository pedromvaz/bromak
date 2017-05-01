package com.bromakgame.learning

class Knowledge {

	String name
	String description

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
