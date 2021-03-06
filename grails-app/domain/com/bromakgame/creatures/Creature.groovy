package com.bromakgame.creatures

import groovy.transform.ToString
import com.bromakgame.learning.SkillLevel
import com.bromakgame.worlds.World

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

	static hasMany = [ learnedSkills : SkillLevel ]
	static belongsTo = [ world : World ]
	Group group

	static constraints = {
		firstName blank: false
		lastName nullable: true
		gender inList: [MALE, FEMALE]
		father nullable: true
		mother nullable: true
		group nullable: true
	}

	static mapping = {
		gender column: "gender", sqlType: "char", length: 1
		learnedSkills lazy: false
	}

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
		def consonants = "bcdfghjklmnpqrstvwxz".split("")
		def vowels = "aeiouy".split("")
		String word = ""

		for (int i = 0; i < format.length(); i++) {
			int c = new Random().nextInt(consonants.size())
			int v = new Random().nextInt(vowels.size())
			String randLetter = (format[i] == "C") ? consonants[c] : vowels[v]

			word += (i == 0) ? randLetter.toUpperCase() : randLetter
		}

		return word
	}
}
