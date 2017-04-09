package com.bromakgame

import groovy.transform.ToString

@ToString
class Creature {
	
	private static final long serialVersionUID = 1
	
	String firstName
	String lastName
	String title
	
	Race race
	
    static constraints = {
		firstName blank: false
		lastName nullable: true
		title nullable: true
    }
}
