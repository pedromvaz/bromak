package com.bromakgame.quests

class QuestType {
	String name
	String description
	String image			// name of the image that represents the quest type (found under assets/images/quests)
	String action			// label shown on the quest button that starts the quest
	int groupCap

	static hasMany = [ objectives : Objective ]

	static constraints = {
		name blank: false, unique: true
		description nullable: true, blank: false
		objectives nullable: true
		groupCap min: 1
	}
}
