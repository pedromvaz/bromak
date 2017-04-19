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
	
    static constraints = {
		name blank: false, unique: true
		description blank: false
		startingPopulation min: 1
    }
}
