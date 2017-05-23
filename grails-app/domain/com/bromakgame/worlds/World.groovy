package com.bromakgame.worlds

/**
 * A world is a grid of adjacent regions, all correctly placed since regions are hexagon-like.
 * 
 * The world's radius defines how many "levels" of regions it contains:
 * - A 0-radius world only contains one region
 * - A 1-radius world is a 0-radius world plus an outer "ring" of 6 adjacent regions
 * - A 2-radius world is a 1-radius world plus an outer "ring" of 12 adjacent regions
 * - And so forth, and so forth
 * 
 * See the following image for clarification: http://alumnus.caltech.edu/~leif/FRP/UG_ChamberGMH.png
 */
class World {
	int radius

	public World(int radius) {
		this.radius = radius

		this.addToRegions(new Region(0.0, 0.0))

		for (int r = 0; r <= radius; r++) {
			
		}
	}

	static hasMany = [ regions : Region ]

	static constraints = {
		radius min: 0
	}

	void generate() {
		
	}
}

/*
public static final double MINIMUM_RADIUS = 3.0

Random rand = new Random()

void generate() {
	double startingAngle = rand.nextFloat() * 2 * Math.PI
	double startingRadius = Math.min(width, height) * 0.5

	for (int i = 0; i < 6; i++) {
		double x = startingRadius * Math.cos(startingAngle + i * Math.PI / 3)
		double y = startingRadius * Math.sin(startingAngle + i * Math.PI / 3)

		Region region = new Region(x: x, y: y)
		this.addToRegions(region)

		generateSubRegions(region, startingRadius)
	}

	this.save(flush: true)

	println(regions)
}

void generateSubRegions(Region region, double radius) {
	double startingAngle = rand.nextFloat() * 2 * Math.PI
	int numChildren = Math.round(4 + 4 * rand.nextInt())
	double angle = 2 * Math.PI / numChildren
	radius /= 2

	for (int i = 0; i < numChildren; i++) {
		double x = radius * Math.cos(startingAngle + i * angle)
		double y = radius * Math.sin(startingAngle + i * angle)

		Region subRegion = new Region(x: x, y: y)
		this.addToRegions(subRegion)

		if (radius > MINIMUM_RADIUS) {
			generateSubRegions(subRegion, radius)
		}
	}
}
*/
