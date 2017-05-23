package com.bromakgame.worlds

/**
 * A region represents a formation of 7 hexagonal areas: 1 central area and 6 adjacent ones.
 * These areas share the same type of terrain, temperature, precipitation and biome (flora, fauna).
 */
class Region {
	double x
	double y

	public Region(double x, double y) {
		double stride = 2 * Area.HEX_MINIMAL_RADIUS

		this.x = x
		this.y = y

		this.addToAreas(new Area(x, y))

		for (int a = 0; a < 6; a++) {
			this.addToAreas(
				new Area(
					x + stride * Math.cos(Math.PI / 3 * a),
					y + stride * Math.sin(Math.PI / 3 * a)))
		}
	}

	static hasMany = [ areas : Area ]
	static belongsTo = [ world : World ]

	static constraints = {
	}
}
