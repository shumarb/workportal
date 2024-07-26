/**
 * Unit tests for RegistrationController class.
 */

package com.example.WorkPortal.controller;
import com.example.WorkPortal.service.RegistrationService;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationControllerTest {

    @Mock
    private RegistrationService registrationService;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    RegistrationController registrationController;

    private String validName;
    private String validUsername;
    private String validEmail;
    private String validPassword;

    private String invalidName;
    private String invalidUsername;
    private String invalidEmail;
    private String invalidPassword;

    @BeforeEach
    public void setUp() {
        validName = "Ali Hassan";
        validUsername = "ali_hassan";
        validEmail = "ali_hassan@icloud.com";
        validPassword = "SS23!";

        invalidName = "Ali";
        invalidUsername = "al";
        invalidEmail = "alan.m";
        invalidPassword = "cx!";
    }

    @Test
    void test_retrievesRegistrationPage() {
        // Act
        String viewName = registrationController.registerPerson(validName, validUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
    }

    @Test
    void test_doesNotRetrieveIndexPage() {
        // Act
        String viewName = registrationController.registerPerson(validName, validUsername, validEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertNotEquals("index", viewName);
    }

    @Test
    void test_doesNotRetrieveLoginPage() {
        // Act
        String viewName = registrationController.registerPerson(validName, validUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertNotEquals("login", viewName);
    }

    @Test
    void test_registerPerson_success() throws Exception {
        // Arrange
        when(registrationService.isValidEmailAddress(validEmail)).thenReturn(true);
        when(registrationService.isValidName(validName)).thenReturn(true);
        when(registrationService.isValidUsername(validUsername)).thenReturn(true);
        when(registrationService.isValidPassword(validPassword)).thenReturn(true);
        when(registrationService.isEmailAddressRegistered(validEmail)).thenReturn(false);
        when(registrationService.isPasswordRegistered(validPassword)).thenReturn(false);
        when(registrationService.isUsernameRegistered(validUsername)).thenReturn(false);

        // Act
        String viewName = registrationController.registerPerson(validName, validUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("redirect:/login", viewName);
        verify(registrationService).registerPerson(validName, validUsername, validEmail, validPassword, "User");
        verify(redirectAttributes).addFlashAttribute("successfulRegistrationMessage", "Registration successful. Please log in.");
    }

    @Test
    void test_registerPerson_invalidEmail() {
        // Arrange
        when(registrationService.isValidEmailAddress(invalidEmail)).thenReturn(false);

        // Act
        String viewName = registrationController.registerPerson(validName, validUsername, invalidEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid email address entered. Please enter a valid email address.");
    }

    @Test
    void test_registerPerson_invalidName() {
        // Arrange
        when(registrationService.isValidEmailAddress(validEmail)).thenReturn(true);
        when(registrationService.isValidName(invalidName)).thenReturn(false);

        // Act
        String viewName = registrationController.registerPerson(invalidName, validUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid name entered. Please enter a valid name.");
    }

    @Test
    void test_registerPerson_invalidPassword() {
        // Arrange
        when(registrationService.isValidEmailAddress(validEmail)).thenReturn(true);
        when(registrationService.isValidName(validName)).thenReturn(true);
        when(registrationService.isValidPassword(invalidPassword)).thenReturn(false);

        // Act
        String viewName = registrationController.registerPerson(validName, validUsername, validEmail, invalidPassword, "Manager", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid password entered. Please enter a valid password.");
    }

    @Test
    void test_registerPerson_invalidUsername() {
        // Arrange
        when(registrationService.isValidEmailAddress(validEmail)).thenReturn(true);
        when(registrationService.isValidName(validName)).thenReturn(true);
        when(registrationService.isValidPassword(validPassword)).thenReturn(true);
        when(registrationService.isValidUsername(invalidUsername)).thenReturn(false);

        // Act
        String viewName = registrationController.registerPerson(validName, invalidUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid username entered. Please enter a valid username.");
    }



}