package com.mike

import groovy.util.logging.Slf4j
import spock.lang.Specification
import spock.lang.Stepwise

/**
 * Created by Michael Brennan on 9/9/15.
 */
@Slf4j
@Stepwise
class TestMe extends Specification{

    def "Test Hello"(){
        when:
        def s = "hello"

        then:
        s == "hello"
    }
}
