package com.bromakgame.learning

class Epoch {

	String name
	String description

	Set<Technology> technologies = new HashSet<>()

	boolean add(Technology tech) {
		technologies.add(tech)
	}

	boolean remove(Technology tech) {
		technologies.remove(tech)
	}

	int size() {
		technologies.size()
	}

	boolean isEmpty() {
		technologies.isEmpty()
	}

	boolean contains(Technology tech) {
		technologies.contains(tech)
	}

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
