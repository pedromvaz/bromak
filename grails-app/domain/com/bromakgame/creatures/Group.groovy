package com.bromakgame.creatures

import com.bromakgame.Creature
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
	
	String toString() {
		this.class.getSimpleName() + " " + this.id
	}

    static constraints = {
    }
	
	static mapping = {
		 table '`group`'
	}
}
