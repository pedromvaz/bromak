package com.bromakgame.creatures

import groovy.transform.ToString
import com.bromakgame.quests.Quest

@ToString
class Group {

	String toString() {
		this.class.getSimpleName() + " " + this.id
	}

	static hasMany = [ creatures : Creature, quests: Quest ]

	static constraints = {
		creatures nullable: true
	}

	static mapping = {
		 table '`group`'
	}
}
