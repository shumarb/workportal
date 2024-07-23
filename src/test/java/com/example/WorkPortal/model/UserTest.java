/**
 * Unit tests for {@link User} class methods.
 */

package com.example.WorkPortal.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    /**
     * Tests the default constructor of {@link User}.
     */
    @Test
    public void test_defaultUserConstructor() {
        Person user = new User();
        assertNotNull(user);
    }

    /**
     * Tests the constructor of {@link User} with parameters,
     * as well as its getter methods.
     */
    @Test
    public void test_userConstructorWithParameters_and_getterMethods() {
        String name = "Ali Hassan";
        String username = "ali_hassan";
        String email = "ali_hassan@gmail.com";
        String password = "MP092!";
        String role = "User";

        Person user = new User(name, username, email, password);

        assertEquals(name, user.getName());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    /**
     * Tests the {@code toString()} method of {@link User}.
     */
    @Test
    public void test_toStringMethod() {
        String name = "Mary Jane";
        String username = "maryjane";
        String email = "maryjane@outlook.com";
        String password = "XX34!!";
        String role = "User";

        Person user = new User(name, username, email, password);

        String expectedString = "Person {"
                                    + "id = " + 0
                                    + ", name = '" + name
                                    + "', username = '" + username
                                    + "', email = '" + email
                                    + "', password = '" + password
                                    + "', role = '" + role + "'}";
        assertEquals(expectedString, user.toString());
    }

}