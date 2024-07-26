/**
 * Unit tests for LoginController class.
 */
package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.InvalidUsernameOrPasswordException;
import com.example.WorkPortal.model.Manager;
import com.example.WorkPortal.model.Person;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginControllerTest {

    String validName;
    String validUsername;
    String validEmail;
    String validPassword;
    String invalidUsername;
    String invalidPassword;

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
        validName = "Ali Hassan";
        validUsername = "ali_hassan";
        validEmail = "ali_hassan@yahoo.com";
        validPassword = "TY43#";
        invalidUsername = "ad";
        invalidPassword = "12";
    }

    @Test
    void test_showLoginPage() {
        // Act
        String viewName = this.loginController.showLoginPage();

        // Assert
        assertEquals("login", viewName);
    }

    @Test
    void test_doesNotShowIndexPage() {
        // Act
        String viewName = this.loginController.showLoginPage();

        // Assert
        assertNotEquals("index", viewName);
    }

    @Test
    void test_doesNotShowRegistrationPage() {
        // Act
        String viewName = this.loginController.showLoginPage();

        // Assert
        assertNotEquals("registration", viewName);
    }

}