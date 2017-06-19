package com.bromakgame.quests

import com.bromakgame.learning.SkillCategory

class Objective {
	String description
	SkillCategory skillCategory

	static belongsTo = [ questType : QuestType ]

	static constraints = {
		description blank: false
	}
}
