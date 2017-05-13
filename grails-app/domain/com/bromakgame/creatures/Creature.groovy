package com.bromakgame.creatures

import groovy.transform.ToString
import com.bromakgame.learning.Skill

@ToString
class Creature {

	public static final String MALE = "m"
	public static final String FEMALE = "f"

	String firstName
	String lastName

	Race race
	String gender
	boolean alive = true

	Creature father
	Creature mother

	static hasMany = [ learnedSkills : Skill ]

	String getFullName() {
		String fullName = firstName.trim()
		
		if (lastName?.trim()) {
			fullName = fullName + " " + lastName.trim()
		}
		
		return fullName
	}

	String getGenderDesc() {
		return (gender == MALE) ? "Male" : "Female"
	}

	boolean isMale() {
		return gender == MALE
	}

	boolean isFemale() {
		return gender == FEMALE
	}

	static String createRandomWord() {
		def formats = "CCV CVC CVV VCC VCV VVC"
		formats += " CCVC CCVV CVCC CVCV CVVC"
		formats += " VCCV VCVC VCVV VVCC VVCV"
		formats = formats.split()

		int f = new Random().nextInt(formats.size())

		return createRandomWordWithFormat(formats[f])
	}

	static String createRandomWordWithFormat(String format) {
		def consonants = "bcdfghjklmnpqrstvwxyz".split("")
		def vowels = "aeiou".split("")
		String word = ""

		for (int i = 0; i < format.length(); i++) {
			int c = new Random().nextInt(consonants.size())
			int v = new Random().nextInt(vowels.size())
			String randLetter = (format[i] == "C") ? consonants[c] : vowels[v]

			word += (i == 0) ? randLetter.toUpperCase() : randLetter
		}

		return word
	}

    static constraints = {
		firstName blank: false
		lastName nullable: true
		gender inList: [MALE, FEMALE]
		father nullable: true
		mother nullable: true
    }

	// don't know if this is necessary, or implicit
	static mapping = {
		gender column: "gender", sqlType: "char", length: 1
		learnedSkills lazy: false
	}
}
