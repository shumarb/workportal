/**
 * Unit tests for RegistrationPageController class method.
 */
package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationPageControllerTest {

    private String pageName;

    @BeforeEach
    public void setUp() {
        RegistrationPageController registrationPageController = new RegistrationPageController();
        this.pageName = registrationPageController.goToRegistrationPage();
    }

    @Test
    void test_retrievesRegistrationPage() {
        assertEquals("registration", pageName);
    }

    @Test
    void test_doesNotRetrieveIndexPage() {
        assertNotEquals("index", pageName);
    }

    @Test
    void test_doesNotRetrieveLoginPage() {
        assertNotEquals("login", pageName);
    }

}