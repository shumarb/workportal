/**
 * Unit tests for HomeController.
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class HomeControllerTest {

    @Mock
    private HttpSession httpSession;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_showHomePage_success() {
        // Arrange
        Person loggedInPerson = new User("Ali Hassan", "alihassan", "alihassan@gmail.com", "password");
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInPerson);

        // Act
        String viewName = homeController.showHome(httpSession, model);

        // Assert
        assertEquals("home", viewName);
        verify(model).addAttribute("loggedInPerson", loggedInPerson);

    }

}