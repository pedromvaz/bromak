package com.bromakgame.quests

import com.bromakgame.creatures.Group

class QuestRun {
	Group group

	static belongsTo = [ quest : Quest ]

	static constraints = {
		group nullable: false
	}
}
