package com.bromakgame.creatures

import com.bromakgame.learning.SkillTree

class Race {

	String name
	String description
	boolean intelligent
	boolean enabled
	int startingPopulation

	SkillTree skillTree

	static def getPlayableRaces() {
		findAllByIntelligentAndEnabled(true, true)
	}

    static constraints = {
		name blank: false, unique: true
		description blank: false
		startingPopulation min: 1
		skillTree unique: true
    }
}
