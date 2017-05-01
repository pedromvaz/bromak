package com.bromakgame.creatures

import groovy.transform.ToString
import com.bromakgame.learning.Ability

@ToString(excludes='enabled')
class Race {

	String name
	String description
	boolean intelligent
	boolean enabled
	int startingPopulation

	Set<Ability> innateAbilities = new HashSet<>()

	// should only be used for testing purposes
	Race (String name) {
		this.name = name
		this.description = name
		this.intelligent = true
		this.enabled = true
		this.startingPopulation = 3
	}

    static constraints = {
		name blank: false, unique: true
		description blank: false
		startingPopulation min: 1
    }
}
