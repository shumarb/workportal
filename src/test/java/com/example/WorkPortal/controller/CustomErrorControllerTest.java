/**
 * Unit tests for CustomErrorController class.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.model.Manager;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.model.User;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomErrorControllerTest {

    String validName;
    String validUsername;
    String validEmail;
    String validPassword;
    String invalidUsername;
    String invalidPassword;

    @Mock
    private HttpSession httpSession;

    @Mock
    private Model model;

    @InjectMocks
    private CustomErrorController customErrorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validName = "Ali Hassan";
        validUsername = "ali_hassan";
        validEmail = "ali_hassan@yahoo.com";
        validPassword = "TY43#";
        invalidUsername = "ad";
        invalidPassword = "12";
    }

    @Test
    void test_handleError_withLoggedInPerson_user() {
        // Arrange
        Person user = new User(validName, validUsername, validEmail, validPassword);
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(user);

        // Act
        String viewName = customErrorController.handleError(httpSession, model);

        // Assert
        assertEquals("unavailable", viewName);
        verify(httpSession).invalidate();
        verify(model).addAttribute("error", "The page you are looking for is unavailable.");
    }

    @Test
    void test_handleError_withLoggedInPerson_manager() {
        // Arrange
        Person manager = new Manager(validName, validUsername, validEmail, validPassword);
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(manager);

        // Act
        String viewName = customErrorController.handleError(httpSession, model);

        // Assert
        assertEquals("unavailable", viewName);
        verify(httpSession).invalidate();
        verify(model).addAttribute("error", "The page you are looking for is unavailable.");
    }

    @Test
    void test_handleError_withoutLoggedInPerson() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(null);

        // Act
        String viewName = customErrorController.handleError(httpSession, model);

        // Assert
        assertEquals("unavailable", viewName);
        verify(httpSession).invalidate();
        verify(model).addAttribute("error", "The page you are looking for is unavailable.");
    }

}
