package com.bromakgame.math

class Position {
	double x
	double y

	void add(Vector v) {
		this.x += v.x
		this.y += v.y
	}

	void subtract(Vector v) {
		this.x -= v.x
		this.y -= v.y
	}

	static constraints = {
	}
}
