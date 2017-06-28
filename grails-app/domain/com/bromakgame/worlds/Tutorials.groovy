package com.bromakgame.worlds

import com.bromakgame.quests.QuestType

class Tutorials extends World {

	static hasMany = [ questTypes : QuestType ]

	static constraints = {
		questTypes nullable: true
	}
}
