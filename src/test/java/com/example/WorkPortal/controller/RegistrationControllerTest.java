/**
 * Unit tests for RegistrationPageController class method.
 */
package com.example.WorkPortal.controller;
import com.example.WorkPortal.service.RegistrationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    private String pageName;

    @Mock
    private RegistrationService registrationService;

    @BeforeEach
    public void setUp() {
        RegistrationController registrationController = new RegistrationController(registrationService);
        this.pageName = registrationController.showRegistrationPage();
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