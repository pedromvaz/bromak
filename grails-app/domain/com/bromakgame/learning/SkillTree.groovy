package com.bromakgame.learning

import com.bromakgame.creatures.Race

class SkillTree {

	static hasMany = [ skills : Skill ]
	static belongsTo = [ race : Race ]

	static constraints = {
	}
}
