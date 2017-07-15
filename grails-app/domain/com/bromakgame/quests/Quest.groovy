package com.bromakgame.quests

import com.bromakgame.creatures.Group
import com.bromakgame.worlds.Area

import com.bromakgame.creatures.Creature

class Quest {
	QuestType type
	String frequency
	Date dateCreated

	// someone has to own the quest, otherwise it's not accessible
	Group owningGroup
	Creature owningCreature
	Area area

	String getFrequencyDesc() {
		String result
		
		switch (frequency) {
			case FREQUENCY_UNIQUE:
				result = "Unique"
				break
			case FREQUENCY_RARE:
				result = "Rare"
				break
			case FREQUENCY_REPEATABLE:
				result = "Repeatable"
				break
			case FREQUENCY_DAILY:
				result = "Daily"
				break
			default:
				result = "Unknown"
		}
		
		result
	}
	
	String getTitle() {
		return type.name
	}
	
	String getDescription() {
		return type.description
	}
	
	String getImage() {
		return type.image
	}
	
	String getAction() {
		return type.action
	}

	public static final String FREQUENCY_UNIQUE = "u"
	public static final String FREQUENCY_RARE = "r"
	public static final String FREQUENCY_REPEATABLE = "p"
	public static final String FREQUENCY_DAILY = "d"
	
	static hasMany = [ runs : QuestRun ]

	static constraints = {
		frequency inList: [FREQUENCY_UNIQUE, FREQUENCY_RARE, FREQUENCY_REPEATABLE, FREQUENCY_DAILY]
		owningGroup nullable: true
		owningCreature nullable: true
		area nullable: true
	}

	static mapping = {
		frequency column: "frequency", sqlType: "char", length: 1
	}
}
