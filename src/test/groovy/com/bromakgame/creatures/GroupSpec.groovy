package com.bromakgame.creatures

import grails.test.mixin.*
import spock.lang.*
import com.bromakgame.Creature
import com.bromakgame.Race

@TestFor(Group)
class GroupSpec extends Specification {

	@Shared human
	@Shared c1, c2, c3
	
	def group
	
	def setupSpec() {
		human = new Race(name: 'Human')
		c1 = new Creature(race: human, gender: 'm')
		c2 = new Creature(race: human, gender: 'f')
		c3 = new Creature(race: human, gender: 'f')
	}
	
    def setup() {
		group = new Group()
		assert group.size() == 0
		assert group.isEmpty()
    }

    def cleanup() {
    }
	
	def cleanupSpec() {
	}

	void "Test adding Creatures to Group" () {
		when: "We add one Creature to the Group"
			group.add(c1)
		
		then: "The Group is not empty, and contains that Creature"
			assert !group.isEmpty()
			assert group.size() == 1
			assert group.contains(c1)
			assert !group.contains(c2)
			assert !group.contains(c3)
		
		when: "We add another Creature to the Group"
			group.add(c3)
		
		then: "The Group is still not empty, and contains both Creatures"
			assert !group.isEmpty()
			assert group.size() == 2
			assert group.contains(c1)
			assert !group.contains(c2)
			assert group.contains(c3)
		
		when: "We add a Creature already in the Group"
			group.add(c1)
		
		then: "The Group suffers no changes in its size and contents"
			assert !group.isEmpty()
			assert group.size() == 2
			assert group.contains(c1)
			assert !group.contains(c2)
			assert group.contains(c3)
	}
	
	void "Test removing Creatures from Group" () {
		when: "We add several Creatures to the Group"
			group.add(c1)
			group.add(c2)
			group.add(c3)
		
		then: "The Group is not empty, and contains those Creatures"
			assert !group.isEmpty()
			assert group.size() == 3
			assert group.contains(c1)
			assert group.contains(c2)
			assert group.contains(c3)
		
		when: "We remove one Creature from the Group"
			group.remove(c1)
		
		then: "The Group is not empty, and no longer contains that Creature"
			assert !group.isEmpty()
			assert group.size() == 2
			assert !group.contains(c1)
			assert group.contains(c2)
			assert group.contains(c3)
		
		when: "We remove another Creature from the Group"
			group.remove(c2)
		
		then: "The Group is not empty, and no longer contains that Creature"
			assert !group.isEmpty()
			assert group.size() == 1
			assert !group.contains(c1)
			assert !group.contains(c2)
			assert group.contains(c3)
		
		when: "We remove a Creature that is not in the Group"
			group.remove(c1)
		
		then: "The Group suffers no changes in its size and contents"
			assert !group.isEmpty()
			assert group.size() == 1
			assert !group.contains(c1)
			assert !group.contains(c2)
			assert group.contains(c3)
	}
}
