package com.bromakgame.learning

import com.bromakgame.creatures.Creature

class SkillLevel {

	double level
	Skill skill
	
	static belongsTo = [ creature : Creature ]

	static constraints = {
		level min: 0
	}
}
