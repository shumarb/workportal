/**
 * Unit tests for {@link Manager} class methods.
 */

package com.example.WorkPortal.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManagerTest {
    /**
     * Tests the default constructor of {@link Manager}.
     */
    @Test
    public void testDefaultUserConstructor() {
        Person manager = new Manager();
        assertNotNull(manager);
    }

    /**
     * Tests the constructor of {@link Manager} with parameters,
     * as well as its getter methods.
     */
    @Test
    public void testUserConstructorWithParametersAndGetterMethods() {
        String name = "Ali Hassan";
        String username = "ali_hassan";
        String email = "ali_hassan@gmail.com";
        String password = "MP092!";
        String role = "Manager";

        Person manager = new Manager(name, username, email, password);

        assertEquals(name, manager.getName());
        assertEquals(username, manager.getUsername());
        assertEquals(email, manager.getEmail());
        assertEquals(password, manager.getPassword());
        assertEquals(role, manager.getRole());
    }

    /**
     * Tests the {@code toString()} method of {@link Manager}.
     */
    @Test
    public void testToStringMethod() {
        String name = "Mary Jane";
        String username = "maryjane";
        String email = "maryjane@outlook.com";
        String password = "XX34!!";
        String role = "Manager";

        Person manager = new Manager(name, username, email, password);

        String expectedString = "Person {"
                + "id = " + 0
                + ", name = '" + name
                + "', username = '" + username
                + "', email = '" + email
                + "', password = '" + password
                + "', role = '" + role + "'}";

        assertEquals(expectedString, manager.toString());
    }
}
