package com.blogspot.przybyszd.spock.intro01

import com.blogspot.przybyszd.spock.dto.Person
import spock.lang.Specification

class PersonTest extends Specification {
    def "should set first name from constructor 2"() {
        when: "person with set first name"
            Person person = new Person(firstName: "Bob")
        then: "person has first name"
            person.firstName == "Bob"
    }

    def "should set first name from constructor and change with setter"() {
        when:
            Person person = new Person(firstName: "Bob")
        then:
            person.firstName == "Bob"
        when:
            person.firstName = "Tom"
        then:
            person.firstName == "Tom"
    }

    def "should set first name and last name"() {
        when:
            Person person = new Person(firstName: "Bob", lastName: "Smith")
        then:
            person.firstName == "Bob"
        and:
            person.lastName == "Smith"
    }

    def "should compare person with equals"() {
        expect:
            new Person("Bob", "Smith", 15) == new Person("Bob", "Smith", 15)
    }

}
