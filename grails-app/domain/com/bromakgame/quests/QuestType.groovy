package com.bromakgame.quests

import com.bromakgame.learning.Skill

class QuestType {
	String name
	String description
	int groupCap

	static hasMany = [ objectives : Objective ]

	static constraints = {
		groupCap min: 1
	}
}
