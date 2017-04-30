package com.bromakgame.creatures

import groovy.transform.ToString

@ToString
class Group {
	
	Set<Creature> creatures = new HashSet<>()
	
	boolean add(Creature creature) {
		creatures.add(creature)
	}
	
	boolean remove(Creature creature) {
		creatures.remove(creature)
	}
	
	int size() {
		creatures.size()
	}
	
	boolean isEmpty() {
		creatures.isEmpty()
	}
	
	boolean contains(Creature creature) {
		creatures.contains(creature)
	}
	
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
	
	String toString() {
		this.class.getSimpleName() + " " + this.id
	}

    static constraints = {
    }
	
	static mapping = {
		 table '`group`'
	}
}
