package com.example.WorkPortal.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    public void test_defaultUserConstructor() {
        User user = new User();
        assertNotNull(user);
    }

    @Test
    public void test_userConstructorWithParameters() {
        String name = "Ali Hassan";
        String username = "ali_hassan";
        String email = "ali_hassan@gmail.com";
        String password = "MP092!";
        String role = "User";

        User user = new User(name, username, email, password);

        assertEquals(name, user.getName());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(role, user.getRole());
    }

    @Test
    public void test_toStringMethod() {
        String name = "Mary Jane";
        String username = "maryjane";
        String email = "maryjane@outlook.com";
        String password = "XX34!!";
        String role = "User";
        User user = new User(name, username, email, password);

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