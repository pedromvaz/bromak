package com.bromakgame.worlds

import com.bromakgame.math.Position
import com.bromakgame.math.Vector
import com.bromakgame.users.User
import com.bromakgame.creatures.Creature

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
	String name
	int radius
	int maxNumPlayers
	boolean finished = false
	Date dateCreated

	// players rel is only filled if maxNumPlayers > 1
	static hasMany = [ regions : Region, players : User, creatures : Creature ]
	static belongsTo = [ owner : User ]

	static constraints = {
		radius min: 0
		maxNumPlayers min: 1
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
					/*
					this.addToRegions(
						new Region(
							next_region.x,
							next_region.y,
							Region.getRandomHeightBetween(
								Region.TOPOGRAPHY_EPIPELAGIC_START,
								Region.TOPOGRAPHY_MOUNTAIN_START)))
					*/
					this.addToRegions(
						new Region(
							next_region.x,
							next_region.y,
							Region.getRandomHeightBetween(
								Region.TOPOGRAPHY_OCEAN_FLOOR,
								Region.TOPOGRAPHY_MOUNTAIN_TOP)))
				}
			}
			
			// we must rotate 60 degrees clockwise
			// before heading into the next ring
			next_region_v1.rotate(-Math.PI / 3.0)
			next_region_v2.rotate(-Math.PI / 3.0)
		}
	}
}
