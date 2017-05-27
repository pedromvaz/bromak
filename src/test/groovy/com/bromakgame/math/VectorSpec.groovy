package com.bromakgame.math

import grails.test.mixin.*
import spock.lang.*

@TestFor(Vector)
class VectorSpec extends Specification {
	static final double NUM_DECIMAL_PLACES = Math.pow(10.0, 12.0)
	Vector v

	def setup() {
		v = new Vector(magnitude: 4.0, direction: Math.PI / 4)
		
		assert truncate(v.getX()) == truncate(Math.sqrt(8.0))
		assert truncate(v.getY()) == truncate(Math.sqrt(8.0))
	}

	def cleanup() {
	}

	double truncate(double value) {
		return Math.round(value * NUM_DECIMAL_PLACES) / NUM_DECIMAL_PLACES
	}

	void "Test rotating the vector"() {
		when:"The vector is rotated counter-clockwise"
			v.rotate(Math.PI / 2)
		
		then:"The vector's direction is updated, and thus its X and Y values"
			truncate(v.getX()) == - truncate(Math.sqrt(8.0))
			truncate(v.getY()) == truncate(Math.sqrt(8.0))
		
		when:"The vector is rotated clockwise"
			v.rotate(- 3 * Math.PI / 4)
		
		then:"The vector's direction is updated, and thus its X and Y values"
			v.getX() == 4.0
			v.getY() == 0.0
		
		when:"The vector is rotated counter-clockwise for more than 360 degrees"
			v.rotate(9 * Math.PI / 4)
		
		then:"The vector's direction is updated, and thus its X and Y values"
			truncate(v.getX()) == truncate(Math.sqrt(8.0))
			truncate(v.getY()) == truncate(Math.sqrt(8.0))
	}

	void "Test scaling the vector (i.e. changing its magnitude)"() {
		when:"The vector is scaled up"
			v.scale(3.0)
		
		then:"The vector's magnitude increases, changing its X and Y values"
			truncate(v.getX()) == truncate(Math.sqrt(72))
			truncate(v.getY()) == truncate(Math.sqrt(72))
		
		when:"The vector is scaled down"
			v.scale(0.5)
		
		then:"The vector's magnitude decreases, changing its X and Y values"
			truncate(v.getX()) == truncate(Math.sqrt(18))
			truncate(v.getY()) == truncate(Math.sqrt(18))
		
		when:"The vector is scaled negatively"
			v.scale(-1.0)
		
		then:"The vector's magnitude is updated and its direction rotated 180 degrees, changing its X and Y values"
			truncate(v.getX()) == - truncate(Math.sqrt(18))
			truncate(v.getY()) == - truncate(Math.sqrt(18))
	}
}
