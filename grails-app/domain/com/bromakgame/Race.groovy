package com.bromakgame

import groovy.transform.ToString

@ToString(excludes='enabled')
class Race {
	
	private static final long serialVersionUID = 1
	
	String name
	String description
	boolean intelligent
	boolean enabled
	int startingPopulation
	
	// should only be used for testing purposes
	Race (String name) {
		this.name = name
		this.description = name
		this.intelligent = true
		this.enabled = true
		this.startingPopulation = 3
	}
	
    static constraints = {
		name blank: false, unique: true
		description blank: false
		startingPopulation min: 1
    }
}
