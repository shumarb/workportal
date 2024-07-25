/**
 * Unit tests for HomeController class.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.model.User;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HomeControllerTest {

    @Mock
    Person loggedInPerson;

    @Mock
    private HttpSession httpSession;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        loggedInPerson = new User("Ali Hassan", "ali_hassan", "alihassan@gmail.com", "password");
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_showHomePage_success() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInPerson);

        // Act
        String viewName = homeController.showHome(httpSession, model);

        // Assert
        assertEquals("home", viewName);
        verify(model).addAttribute("loggedInPerson", loggedInPerson);
    }

    @Test
    void test_logoutOfHome() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInPerson);

        // Act
        String viewName = homeController.logoutOfHome(httpSession, model);

        // Assert
        assertEquals("index", viewName);
        verify(httpSession).invalidate();
        verify(model).addAttribute("logout", "You have been successfully logged out.");
    }

}