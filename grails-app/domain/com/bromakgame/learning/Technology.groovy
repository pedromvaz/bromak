package com.bromakgame.learning

class Technology {

	String name
	String description

	Set<Skill> skills = new HashSet<>()
	
	int size() {
		skills.size()
	}

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
