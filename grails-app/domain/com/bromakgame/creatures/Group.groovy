package com.bromakgame.creatures

import com.bromakgame.Creature

class Group {
	
	private static final long serialVersionUID = 1
	
	int totalCreatures
	
	Set<Creature> champions = new HashSet<>()

    static constraints = {
		totalCreatures min: 0
    }
	
	static mapping = {
		 table '`group`'
	}
}
