package com.bromakgame.creatures

import groovy.transform.ToString
import com.bromakgame.learning.Skill

@ToString
class Creature {

	public static final String MALE = "m"
	public static final String FEMALE = "f"

	Race race
	String gender
	boolean alive = true

	Creature father
	Creature mother

	Set<Skill> learnedSkills = new HashSet<>()

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
