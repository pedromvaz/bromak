package com.bromakgame.creatures

class Community extends Group {

	Set<Group> groups = new HashSet<>()
	
	boolean add(Group group) {
		groups.add(group)
	}
	
	boolean remove(Group group) {
		groups.remove(group)
	}
	
	int size() {
		int size = 0
		
		for (Iterator<Group> it = groups.iterator(); it.hasNext(); ) {
			Group g = it.next()
			size += g.size()
		}
		
		size += super.size()
		
		return size
	}
	
    static constraints = {
    }
}
