package com.bromakgame.math

import grails.test.mixin.*
import spock.lang.*

@TestFor(Position)
class PositionSpec extends Specification {
	Position p
	Vector v1, v2, v3, v4

	def setup() {
		p = new Position(x: 2.0, y:4.0)
		v1 = new Vector(magnitude: Math.sqrt(2.0), direction: Math.PI / 4)
		v2 = new Vector(magnitude: 3.0, direction: Math.PI / 2)
		v3 = new Vector(magnitude: 4.0, direction: 0.0)
		v4 = new Vector(magnitude: Math.sqrt(2.0), direction: 9 * Math.PI / 4)	// 360 + 45 degrees
	}

	def cleanup() {
	}

	void "Test adding vector to position"() {
		when:"The vector is added to the position"
			p.add(v1)
		
		then:"The position is updated"
			Math.round(p.getX()) == 3.0
			Math.round(p.getY()) == 5.0
		
		when:"The vector is added to the position"
			p.add(v2)
		
		then:"The position is updated"
			Math.round(p.getX()) == 3.0
			Math.round(p.getY()) == 8.0
		
		when:"The vector is added to the position"
			p.add(v3)
		
		then:"The position is updated"
			Math.round(p.getX()) == 7.0
			Math.round(p.getY()) == 8.0
		
		when:"The vector is added to the position"
			p.add(v4)
		
		then:"The position is updated"
			Math.round(p.getX()) == 8.0
			Math.round(p.getY()) == 9.0
	}

	void "Test subtracting vector from position"() {
		when:"The vector is subtracted from the position"
			p.subtract(v1)
		
		then:"The position is updated"
			Math.round(p.getX()) == 1.0
			Math.round(p.getY()) == 3.0
		
		when:"The vector is subtracted from the position"
			p.subtract(v2)
		
		then:"The position is updated"
			Math.round(p.getX()) == 1.0
			Math.round(p.getY()) == 0.0
		
		when:"The vector is subtracted from the position"
			p.subtract(v3)
		
		then:"The position is updated"
			Math.round(p.getX()) == -3.0
			Math.round(p.getY()) == 0.0
		
		when:"The vector is subtracted from the position"
			p.subtract(v4)
		
		then:"The position is updated"
			Math.round(p.getX()) == -4.0
			Math.round(p.getY()) == -1.0
	}
}
