package com.bromakgame.math

class Vector {
	double magnitude
	double direction		// in radians

	void rotate(double angle_rad) {
		this.direction += angle_rad
	}

	void scale(double scale) {
		this.magnitude *= scale
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
	}
}
