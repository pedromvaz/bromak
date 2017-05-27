package com.bromakgame.worlds

class Area {
	public static final double HEX_MAXIMAL_RADIUS = 1.0
	public static final double HEX_MINIMAL_RADIUS = HEX_MAXIMAL_RADIUS * Math.cos(Math.PI / 6)

	double x
	double y

	static belongsTo = [ region : Region ]

	static constraints = {
	}

	double getX() {
		return getX(5)
	}

	double getX(int precision) {
		return Math.round(x * Math.pow(10, precision)) / Math.pow(10, precision)
	}

	double getY() {
		return getY(5)
	}

	double getY(int precision) {
		return Math.round(y * Math.pow(10, precision)) / Math.pow(10, precision)
	}
}
