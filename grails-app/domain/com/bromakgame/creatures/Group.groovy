package com.bromakgame.creatures

import groovy.transform.ToString

@ToString
class Group {
	
	Map percentile() {
		int males = 0
		int females = 0
		int maleChampions = 0
		int femaleChampions = 0
		
		for (Iterator<Creature> it = creatures.iterator(); it.hasNext(); ) {
			Creature c = it.next()
			
			if (c.isMale()) {
				if (c.getClass() == Champion)
					maleChampions++
				else
					males++
			} else {
				if (c.getClass() == Champion)
					femaleChampions++
				else
					females++
			}
		}
		
		def percentile = [:]
		percentile << [ males : males ]
		percentile << [ females : females ]
		percentile << [ maleChampions : maleChampions ]
		percentile << [ femaleChampions : femaleChampions ]
		
		if (size() > 0) {
			percentile.keySet().each {
				percentile[it] = percentile[it] / size() * 100
			}
		}
		
		return percentile
	}

	// TODO: This method must be optimized
	int countCreaturesThatKnowSkill(long skillId) {
		int total = 0
		
		for (Iterator<Creature> it = creatures.iterator(); it.hasNext(); ) {
			Creature c = it.next()
			
			if (c.learnedSkills.find { it.id == skillId }) {
				total++
			}
		}
		
		total
	}

	String toString() {
		this.class.getSimpleName() + " " + this.id
	}

	static hasMany = [ creatures : Creature ]

    static constraints = {
		creatures nullable: true
    }

	static mapping = {
		 table '`group`'
	}
}
