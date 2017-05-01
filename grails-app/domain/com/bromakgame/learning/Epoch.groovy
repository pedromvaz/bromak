package com.bromakgame.learning

class Epoch {

	String name
	String description
	
	Set<Technology> technologies = new HashSet<>()

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
