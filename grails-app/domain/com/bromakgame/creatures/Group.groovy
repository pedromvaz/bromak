package com.bromakgame.creatures

import groovy.transform.ToString

@ToString
class Group {

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
