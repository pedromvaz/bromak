package com.bromakgame.worlds

import com.bromakgame.math.Position
import com.bromakgame.math.Vector

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
	}

	static hasMany = [ regions : Region ]

	static constraints = {
		radius min: 0
	}

	void generate() {
		Position next_region = new Position(x: 0.0, y: 0.0)
		Vector next_region_v1 = new Vector(
			magnitude: 3.0 * Area.HEX_MAXIMAL_RADIUS,
			direction: -Math.PI / 6)
		Vector next_region_v2 = new Vector(
			magnitude: 2.0 * Area.HEX_MINIMAL_RADIUS,
			direction: 0.0)

		this.addToRegions(
			new Region(
				next_region.x,
				next_region.y))

		for (int ring = 1; ring <= radius; ring++) {
			// for each radius level (a.k.a. ring), move the starting position
			// towards the region directly below and to the right
			// from the current region (see image referenced in class header)
			next_region.add(next_region_v1)
			next_region.add(next_region_v2)
			
			// we must rotate 120 degrees counter-clockwise
			// (60 here + 60 inside loop) for the first region in the ring
			next_region_v1.rotate(Math.PI / 3.0)
			next_region_v2.rotate(Math.PI / 3.0)
			
			for (int side = 0; side < 6; side++) {
				// rotate both vectors by 60 degrees counter-clockwise
				next_region_v1.rotate(Math.PI / 3.0)
				next_region_v2.rotate(Math.PI / 3.0)
				
				for (int count = 0; count < ring; count++) {
					next_region.add(next_region_v1)
					next_region.add(next_region_v2)
					
					this.addToRegions(
						new Region(
							next_region.x,
							next_region.y,
							Region.getRandomHeightBetween(
								Region.TOPOGRAPHY_EPIPELAGIC_START,
								Region.TOPOGRAPHY_MOUNTAIN_START)))
				}
			}
			
			// we must rotate 60 degrees clockwise
			// before heading into the next ring
			next_region_v1.rotate(-Math.PI / 3.0)
			next_region_v2.rotate(-Math.PI / 3.0)
		}
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
