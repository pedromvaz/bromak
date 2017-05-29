package com.bromakgame.math

class Vector {
	double magnitude
	double direction		// in radians

	void rotate(double angle_rad) {
		this.direction += angle_rad
	}

	void scale(double scale) {
		this.magnitude *= Math.abs(scale)
		
		if (scale < 0)
			this.rotate(Math.PI)
	}

	double getX() {
		return magnitude * Math.cos(direction)
	}

	double getY() {
		return magnitude * Math.sin(direction)
	}

	Position toPosition() {
		return new Position(getX(), getY())
	}

	static constraints = {
		magnitude min: 0.0000000001, scale: 5
		direction scale: 5
	}
}
