package com.bromakgame

class Champion extends Creature {

	// TODO: If we create the Player class, fix this
	static belongsTo = [ user: User ]
	
    static constraints = {
    }
}
