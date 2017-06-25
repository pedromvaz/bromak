package com.bromakgame.users

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import com.bromakgame.creatures.Champion

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable {

	private static final long serialVersionUID = 1

	transient springSecurityService

	String username
	String email
	String password
	Date dateCreated
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this)*.role
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	String encodeActivationString() {
		return springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(activationString()) : null
	}
	
	String activationString() {
		return username + email
	}

	static hasMany = [ champions : Champion ]

	static constraints = {
		password blank: false, password: true
		username blank: false, unique: true
		email blank: false, email: true
	}

	static mapping = {
		password column: '`password`'
	}
}
