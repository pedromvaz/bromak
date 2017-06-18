package com.bromakgame.quests

import com.bromakgame.learning.Skill

class Objective {
	String description

	static hasMany = [ primarySkills : Skill, secondarySkills : Skill ]
	static belongsTo = [ questType : QuestType ]

	static constraints = {
	}
}
