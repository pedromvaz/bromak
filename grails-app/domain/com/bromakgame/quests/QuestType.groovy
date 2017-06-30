package com.bromakgame.quests

class QuestType {
	String name
	String description
	int groupCap

	static hasMany = [ objectives : Objective ]

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
		objectives nullable: true
		groupCap min: 1
	}
}
