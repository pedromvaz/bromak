package com.bromakgame.worlds

class World {

	public static final double MINIMUM_RADIUS = 3.0
	
	int width
	int height
	
	Random rand = new Random()

	static hasMany = [ regions : Region ]

	static constraints = {
	}

	void generate() {
		
	}
}

/*
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
