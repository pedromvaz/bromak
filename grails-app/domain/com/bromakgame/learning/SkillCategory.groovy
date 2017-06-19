package com.bromakgame.learning

import com.bromakgame.quests.Objective

class SkillCategory {
	String name
	String description
	
	static hasMany = [ skills : Skill, objectives : Objective ]
	
	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
	}
}
