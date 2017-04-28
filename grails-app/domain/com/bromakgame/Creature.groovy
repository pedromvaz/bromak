package com.bromakgame

import groovy.transform.ToString

@ToString
class Creature {
	
	private static final long serialVersionUID = 1
	
	public static final String MALE = "m"
	public static final String FEMALE = "f"
	
	Race race
	String gender
	boolean alive = true
	
	Creature father
	Creature mother
	
	String getGenderDesc() {
		return (gender == MALE) ? "Male" : "Female"
	}
	
	boolean isMale() {
		return gender == MALE
	}
	
	boolean isFemale() {
		return gender == FEMALE
	}
	
    static constraints = {
		gender inList: [MALE, FEMALE]
		father nullable: true
		mother nullable: true
    }
	
	// don't know if this is necessary, or implicit
	static mapping = {
		gender column: "gender", sqlType: "char", length: 1
	}
}
