package com.bromakgame.learning

import com.bromakgame.creatures.Race

class Skill {

	String name
	String description

	Set<Race> races = new HashSet<>()

	boolean add(Race race) {
		races.add(race)
	}

	boolean remove(Race race) {
		races.remove(race)
	}

	int size() {
		races.size()
	}

	boolean isEmpty() {
		races.isEmpty()
	}

	boolean contains(Race race) {
		races.contains(race)
	}

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
