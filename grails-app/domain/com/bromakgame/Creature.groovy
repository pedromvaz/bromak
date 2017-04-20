package com.bromakgame

import groovy.transform.ToString

@ToString
class Creature {
	
	private static final long serialVersionUID = 1
	
	Race race
	String gender
	boolean alive = true
	
	Creature father
	Creature mother
	
	String getGenderDesc() {
		return (gender == "m") ? "Male" : "Female"
	}
	
    static constraints = {
		gender inList: ["m", "f"]
		father nullable: true
		mother nullable: true
    }
	
	// don't know if this is necessary, or implicit
	static mapping = {
		gender column: "gender", sqlType: "char", length: 1
	}
}
