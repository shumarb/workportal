/**
 * Unit tests for {@link Person} class methods.
 */

package com.example.WorkPortal.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {

    /**
     * Tests the default constructor of {@link Person}.
     */
    @Test
    public void test_defaultPersonConstructor() {
        Person person = new Person();
        assertNotNull(person);
    }

    /**
     * Tests the constructor of {@link Person} with parameters,
     * as well as its getter methods.
     */
    @Test
    public void test_personConstructorWithParameters_and_getterMethods() {
        String name = "Sam Ray";
        String username = "sam_ray33";
        String email = "sam.ray@gmail.com";
        String password = "sAM@r2";
        String role = "User";

        Person person = new Person(name, username, email, password, role);

        assertEquals(name, person.getName());
        assertEquals(username, person.getUsername());
        assertEquals(email, person.getEmail());
        assertEquals(password, person.getPassword());
        assertEquals(role, person.getRole());
    }

    /**
     * Tests the {@code toString()} method of {@link Person}.
     */
    @Test
    void test_toStringMethod() {
        String name = "May Lim";
        String username = "may_lim99";
        String email = "may_lim@gmail.com";
        String password = "MM@YY1";
        String role = "Manager";

        Person person = new Person(name, username, email, password, role);

        String expectedString = "Person {"
                + "id = " + 0
                + ", name = '" + name
                + "', username = '" + username
                + "', email = '" + email
                + "', password = '" + password
                + "', role = '" + role + "'}";
        assertEquals(expectedString, person.toString());
    }

}
