package com.bromakgame.worlds

class Area {
	public static final double HEX_MAXIMAL_RADIUS = 60.0
	public static final double HEX_MINIMAL_RADIUS = HEX_MAXIMAL_RADIUS * Math.cos(Math.PI / 6)

	double x
	double y

	static belongsTo = [ region : Region ]

	static constraints = {
		x scale: 5
		y scale: 5
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

	String getTopographyIcon() {
		double topography = region.getTopography()
		
		if (topography >= Region.TOPOGRAPHY_HILL_START) {
			return "icon-mountains"
		} else if (topography < Region.TOPOGRAPHY_SEA_LEVEL) {
			return "icon-air"
		} else {
			return ""
		}
	}

	String getTopographyAndBiomeColor() {
		double topography = region.getTopography()
		double biome = 0.0
		
		return ""
		/*
		if (topography >= Region.TOPOGRAPHY_MOUNTAIN_START) {
			return "mountain"
		} else if (topography >= Region.TOPOGRAPHY_HILL_START) {
			return "hill"
		} else if (topography >= Region.TOPOGRAPHY_SEA_LEVEL) {
			return "grasslands"
		} else if (topography >= Region.TOPOGRAPHY_EPIPELAGIC_START) {
			return "shallows"
		} else {
			return "ocean"
		}
		*/
	}
}
