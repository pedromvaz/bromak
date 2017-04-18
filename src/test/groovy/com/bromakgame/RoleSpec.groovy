package com.bromakgame

import grails.test.mixin.*
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Role)
class RoleSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

	void "Test the toString() value, used in Users list"() {

		setup:
			String authority = "ROLE_ADMIN"

		when:
			def role = new Role(authority: authority, description: 'No need')

		then:
			assert role != null
			assert role.toString() == authority
	}
}
