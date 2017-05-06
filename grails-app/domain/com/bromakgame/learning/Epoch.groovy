package com.bromakgame.learning

class Epoch {

	String name
	String description

	Set<Skill> skills = new HashSet<>()

	boolean add(Skill skill) {
		skills.add(skill)
	}

	boolean remove(Skill skill) {
		skills.remove(skill)
	}

	int size() {
		skills.size()
	}

	boolean isEmpty() {
		skills.isEmpty()
	}

	boolean contains(Skill skill) {
		skills.contains(skill)
	}

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
