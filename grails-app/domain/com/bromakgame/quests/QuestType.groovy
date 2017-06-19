package com.bromakgame.quests

import com.bromakgame.learning.Skill

class QuestType {
	String name
	String description
	int groupCap

	static hasMany = [ objectives : Objective ]

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
		groupCap min: 1
	}
}
