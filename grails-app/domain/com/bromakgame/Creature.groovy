package com.bromakgame

import groovy.transform.ToString

@ToString
class Creature {
	
	private static final long serialVersionUID = 1
	
	String firstName
	String lastName
	String title
	
	boolean alive = true
	
	Creature father
	Creature mother
	
	Race race
	String gender
	
	String getFullName() {
		String fullName = firstName
		
		if (title?.trim()) {
			fullName = title + " " + fullName
		}
		
		if (lastName?.trim()) {
			fullName = fullName + " " + lastName
		}
		
		return fullName
	}
	
	String getGenderDesc() {
		return (gender == "m") ? "Male" : "Female"
	}
	
    static constraints = {
		firstName blank: false
		lastName nullable: true
		title nullable: true
		gender inList: ["m", "f"]
		father nullable: true
		mother nullable: true
    }
	
	// don't know if this is necessary, or implicit
	static mapping = {
		gender column: "gender", sqlType: "char", length: 1
	}
}
