package com.bromakgame

import grails.test.mixin.*
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Creature)
class CreatureSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

	@Ignore
    void "test something"() {
        expect:"fix me"
            true == false
    }
}