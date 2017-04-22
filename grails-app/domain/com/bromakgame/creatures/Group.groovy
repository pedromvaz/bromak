package com.bromakgame.creatures

import com.bromakgame.Creature

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

    static constraints = {
    }
	
	static mapping = {
		 table '`group`'
	}
}
