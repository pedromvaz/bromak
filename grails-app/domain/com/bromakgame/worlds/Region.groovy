package com.bromakgame.worlds

/**
 * A region is a collection of areas that share the same type of terrain, temperature,
 * precipitation and biome (flora, fauna).
 * 
 * All areas belonging to a region are closer to its center than any other region's center.
 */
class Region {
	int x
	int y

	static hasMany = [ areas : Area ]
	static belongsTo = [ world : World ]

	static constraints = {
	}

	String toString() {
		return "Region(" + x + ";" + y + ")"
	}
}
