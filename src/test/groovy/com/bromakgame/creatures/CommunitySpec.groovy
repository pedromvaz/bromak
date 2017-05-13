package com.bromakgame.creatures

import grails.test.mixin.*
import spock.lang.*
import com.bromakgame.creatures.Creature
import com.bromakgame.creatures.Race

@TestFor(Community)
class CommunitySpec extends Specification {

	@Shared name
	@Shared human
	@Shared c1, c2, c3, c4, c5, c6, c7
	@Shared group1, group2, group3
	
	def community
	
	def setupSpec() {
		name = new String("Name")
		human = new Race(name: 'Human')
		c1 = new Creature(firstName: name, race: human, gender: 'm')
		c2 = new Creature(firstName: name, race: human, gender: 'f')
		c3 = new Creature(firstName: name, race: human, gender: 'f')
		c3 = new Creature(firstName: name, race: human, gender: 'm')
		c4 = new Creature(firstName: name, race: human, gender: 'm')
		c5 = new Creature(firstName: name, race: human, gender: 'f')
		c6 = new Creature(firstName: name, race: human, gender: 'f')
		c7 = new Creature(firstName: name, race: human, gender: 'm')
		
		group1 = new Group()
		group1.add(c1)
		group1.add(c2)
		group1.add(c3)
		assert group1.size() == 3
		
		group2 = new Group()
		group2.add(c4)
		group2.add(c5)
		assert group2.size() == 2
		
		group3 = new Group()
		group3.add(c6)
		group3.add(c7)
		assert group3.size() == 2
	}
	
    def setup() {
		community = new Community()
		assert community.size() == 0
    }

    def cleanup() {
    }
	
	void "Test adding Groups to the Community" () {
		when: "We add one Group to the Community"
			community.add(group1)
		
		then: "The Community's size is equal to that of the added Group"
			assert community.size() == group1.size()
		
		when: "We add another Group to the Community"
			community.add(group2)
		
		then: "The Community's size is equal to the sum of the two Groups"
			assert community.size() == group1.size() + group2.size()
	}
	
	void "Test removing Groups from the Community" () {
		when: "We add several Groups to the Community"
			community.add(group1)
			community.add(group2)
			community.add(group3)
		
		then: "The Community's size is equal to the sum of those Groups"
			assert community.size() == group1.size() + group2.size() + group3.size()
		
		when: "We remove one Group from the Community"
			community.remove(group2)
		
		then: "The Community's size is equal to the sum of the remaining Groups"
			assert community.size() == group1.size() + group3.size()
		
		when: "We remove another Group from the Community"
			community.remove(group1)
		
		then: "The Community's size is equal to the remaining Group"
			assert community.size() == group3.size()
	}
}
