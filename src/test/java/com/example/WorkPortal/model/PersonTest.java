/**
 * Unit tests for Person class methods.
 */

package com.example.WorkPortal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    private Person person1;

    @BeforeEach
    void setUp() {
        this.person1 = new Person("Sam Ray", "sam_ray33", "sam.ray@gmail.com", "sAM@r2", "User");
    }

    @Test
    void test_PersonInstantiation() {
        assertEquals("Sam Ray", person1.getName());
        assertEquals("sam_ray33", person1.getUsername());
        assertEquals("sam.ray@gmail.com", person1.getEmail());
        assertEquals("sAM@r2", person1.getPassword());
        assertEquals("User", person1.getRole());
    }

    @Test
    void test_settersUpdateAttributesCorrectly() {
        person1.setName("Luke Lee");
        assertEquals(person1.getName(), "Luke Lee");

        person1.setUsername("luke_lee");
        assertEquals(person1.getUsername(), "luke_lee");

        person1.setEmail("luke.lee@outlook.com");
        assertEquals(person1.getEmail(), "luke.lee@outlook.com");

        person1.setPassword("MMAaf_414");
        assertEquals(person1.getPassword(), "MMAaf_414");

        person1.setRole("Manager");
        assertEquals(person1.getRole(), "Manager");
    }

}
