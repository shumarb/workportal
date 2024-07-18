package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private String pageName;

    @BeforeEach
    public void setUp() {
        LoginController loginController = new LoginController();
        this.pageName = loginController.showLoginPage();
    }

    @Test
    void test_retrievesLoginPage() {
        assertEquals("login", pageName);
    }

    @Test
    void test_doesNotRetrieveIndexPage() {
        assertNotEquals("index", pageName);
    }

    @Test
    void test_doesNotRetrieveRegistrationPage() {
        assertNotEquals("registration", pageName);
    }

}