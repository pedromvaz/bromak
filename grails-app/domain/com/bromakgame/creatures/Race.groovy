package com.bromakgame.creatures

import groovy.transform.ToString
import com.bromakgame.learning.Skill

@ToString(excludes='enabled')
class Race {

	String name
	String description
	boolean intelligent
	boolean enabled
	int startingPopulation

	static hasMany = [ learnableSkills : Skill ]

	// should only be used for testing purposes
	Race (String name) {
		this.name = name
		this.description = name
		this.intelligent = true
		this.enabled = true
		this.startingPopulation = 3
	}
	
	static def getPlayableRaces() {
		findAllByIntelligentAndEnabled(true, true)
	}
	
	static def getAllThatCanLearn(long skillId) {
		withCriteria {
			learnableSkills {
				eq 'id', skillId
			}
		}
	}

    static constraints = {
		name blank: false, unique: true
		description blank: false
		startingPopulation min: 1
    }
}
