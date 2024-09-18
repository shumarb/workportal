/**
 * Unit tests for LoginController class.
 */
package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.UnsuccessfulLoginException;
import com.example.WorkPortal.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    String validUsername;
    String validPassword;

    @Mock
    private HttpSession httpSession;

    @Mock
    private LoginService loginService;

    @Mock
    private Model model;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        validUsername = "ali_hassan";
        validPassword = "TY43#";
    }

    @Test
    void test_showLoginPage() {
        // Act
        String viewName = this.loginController.showLoginPage();

        // Assert
        assertEquals("login", viewName);
    }

    @Test
    void testDoesNotShowIndexPage() {
        // Act
        String viewName = this.loginController.showLoginPage();

        // Assert
        assertNotEquals("index", viewName);
    }

    @Test
    void testDoesNotShowRegistrationPage() {
        // Act
        String viewName = this.loginController.showLoginPage();

        // Assert
        assertNotEquals("registration", viewName);
    }

    @Test
    void testUnsuccessfulLoginDueToUnexpectedError() throws UnsuccessfulLoginException {
        // Arrange
        lenient().when(loginService.login(validUsername, validPassword)).thenThrow(new RuntimeException());

        // Act
        String viewName = loginController.loginPerson(validUsername, validPassword, httpSession, model);

        // Assert
        assertEquals("login", viewName);
        verify(model).addAttribute("error", "Unexpected error occurred. Please try again later.");
    }

}