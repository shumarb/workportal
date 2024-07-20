package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    private String pageName;

    @Mock
    private Model model;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
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