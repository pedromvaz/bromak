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

		Area area = new Area(x: x, y: y)
		this.addToAreas(area)
		
		//println(area.getX() + "," + area.getY())

		for (int a = 0; a < 6; a++) {
			area = new Area(
				x: x + stride * Math.cos(Math.PI / 3 * a),
				y: y + stride * Math.sin(Math.PI / 3 * a))
			this.addToAreas(area)
			
			//println(area.getX() + "," + area.getY())
		}
		
		//println()
	}

	static hasMany = [ areas : Area ]
	static belongsTo = [ world : World ]

	static constraints = {
	}
}
