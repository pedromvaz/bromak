package com.bromakgame

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='authority')
@ToString(includes='authority', includeNames=true, includePackage=false)
class Role implements Serializable {

	private static final long serialVersionUID = 1

	String authority
	String description

	static constraints = {
		authority blank: false, unique: true
		description blank: false
	}

	static mapping = {
		cache true
	}
}
