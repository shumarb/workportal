/**
 * Unit tests for HomeController class.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.RestrictedAccessException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        loggedInManager = new Manager("Ali Hassan", "ali_hassan", "alihassan@gmail.com", "PO98!");
        loggedInUser = new User("Imran Khan", "imran_khan", "imrankhan@gmail.com", "VC34$");
    }

    @Test
    void test_showHomePage_success() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInManager);

        // Act
        String viewName = this.homeController.showHome(httpSession, model);

        // Assert
        assertEquals("home", viewName);

        // Verify
        verify(model).addAttribute("loggedInPerson", loggedInManager);
    }

    @Test
    void test_logoutOfHome() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInManager);

        // Act
        String viewName = this.homeController.logoutOfHome(httpSession, model);

        // Assert
        assertEquals("index", viewName);

        // Verify
        verify(httpSession).invalidate();
        verify(model).addAttribute("logout", "You have been successfully logged out.");
    }

    @Test
    void test_showManagerialCodeOfConduct_success() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInManager);

        // Act
        String viewName = this.homeController.showManagerialCodeOfConduct(httpSession, model);

        // Assert
        assertEquals("managerial-code-of-conduct", viewName);
    }

    @Test
    void test_showManagerialCodeOfConduct_failure_user() {
        // Arrange
        when(httpSession.getAttribute("loggedInPerson")).thenReturn(loggedInUser);

        // Act
        String viewName = this.homeController.showManagerialCodeOfConduct(httpSession, model);

        // Assert
        assertEquals("access-denied", viewName);
    }

}