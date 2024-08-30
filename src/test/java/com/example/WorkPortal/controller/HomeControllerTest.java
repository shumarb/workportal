/**
 * Unit tests for HomeController class.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.model.Manager;
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
    Person loggedInManager;

    @Mock
    Person loggedInUser;

    @Mock
    private HttpSession httpSession;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    String viewName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loggedInManager = new Manager("Ali Hassan", "ali_hassan", "alihassan@gmail.com", "PO98!");
        loggedInUser = new User("Imran Khan", "imran_khan", "imrankhan@gmail.com", "VC34$");
        viewName = null;
    }

    @Test
    void testShowHomePageSuccess() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInManager);

        // Act
        viewName = homeController.showHome(httpSession, model);

        // Assert
        assertEquals("home", viewName);
        verify(model).addAttribute("loggedInPerson", loggedInManager);
    }

    @Test
    void testLogoutOfHomeSuccess() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInManager);

        // Act
        viewName = homeController.logoutOfHome(httpSession, model);

        // Assert
        assertEquals("index", viewName);
        verify(httpSession).invalidate();
        verify(model).addAttribute("logout", "You have been successfully logged out.");
    }

    @Test
    void testShowManagerialCodeOfConductSuccess() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInManager);

        // Act
        viewName = homeController.showManagerialCodeOfConduct(httpSession, model);

        // Assert
        assertEquals("managerial-code-of-conduct", viewName);
    }

    @Test
    void testShowManagerialCodeOfConductFailureForUser() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInUser);

        // Act
        viewName = homeController.showManagerialCodeOfConduct(httpSession, model);

        // Assert
        assertEquals("access-denied", viewName);
    }

}