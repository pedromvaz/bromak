package com.bromakgame.worlds

class Area {
	public static final HEX_MAXIMAL_RADIUS = 1.0
	public static final HEX_MINIMAL_RADIUS = HEX_MAXIMAL_RADIUS * Math.cos(Math.PI / 6)

	double x
	double y

	static belongsTo = [ region : Region ]

	static constraints = {
	}
}
