package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageControllerTest {

    private String pageName;

    @BeforeEach
    public void setUp() {
        LoginPageController loginPageController = new LoginPageController();
        this.pageName = loginPageController.goToLoginPage();
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