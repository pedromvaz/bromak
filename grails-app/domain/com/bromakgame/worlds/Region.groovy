package com.bromakgame.worlds

/**
 * A region represents a formation of 7 hexagonal areas: 1 central area and 6 adjacent ones.
 * These areas share the same type of terrain, temperature, precipitation and biome (flora, fauna).
 */
class Region {
	public static final double TOPOGRAPHY_MOUNTAIN_TOP = 2000.0
	public static final double TOPOGRAPHY_MOUNTAIN_START = 600.0
	public static final double TOPOGRAPHY_HILL_START = 100.0
	public static final double TOPOGRAPHY_SEA_LEVEL = 0.0
	public static final double TOPOGRAPHY_EPIPELAGIC_START = -200.0
	public static final double TOPOGRAPHY_MESOPELAGIC_START = -1000.0
	public static final double TOPOGRAPHY_OCEAN_FLOOR = -2000.0

	double x
	double y
	double topography

	public Region(double x, double y) {
		this(x, y, 20.0)	// 20 meters above sea-level by default
	}

	public Region(double x, double y, double topography) {
		double stride = 2 * Area.HEX_MINIMAL_RADIUS

		this.x = x
		this.y = y
		this.topography = topography

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

	static double getRandomHeightBetween(double min, double max) {
		return min + Math.random() * (max - min)
	}

	static hasMany = [ areas : Area ]
	//static hasOne = [ biome : Biome ]
	static belongsTo = [ world : World ]

	static constraints = {
		topography min: TOPOGRAPHY_OCEAN_FLOOR, max: TOPOGRAPHY_MOUNTAIN_TOP, scale: 5
		x scale: 5
		y scale: 5
	}
}
