/**
 * Unit tests for RegistrationController class.
 */

package com.example.WorkPortal.controller;
import com.example.WorkPortal.exceptions.*;
import com.example.WorkPortal.model.Manager;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.service.RegistrationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    // Arrange
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
        MockitoAnnotations.openMocks(this);
        registrationController = new RegistrationController(registrationService);

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
    void testShowRegistrationPage() {
        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
    }

    @Test
    void testDoesNotRetrieveIndexPage() {
        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertNotEquals("index", viewName);
    }

    @Test
    void testDoesNotRetrieveLoginPage() {
        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertNotEquals("login", viewName);
    }

    @Test
    void testSuccessfulRegistration() throws Exception {
        // Arrange
        Person person = new Manager(validName, validUsername, validEmail, validPassword);
        when(registrationService.registration(validName, validUsername, validEmail, validPassword, "Manager")).thenReturn(person);

        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertEquals("redirect:/login", viewName);
        verify(registrationService).registration(validName, validUsername, validEmail, validPassword, "Manager");
        verify(redirectAttributes).addFlashAttribute("successfulRegistrationMessage", "Registration successful. Please log in.");
    }

    @Test
    void testUnsuccessfulRegistrationDueToInvalidEmailAddress() throws  InvalidNameException,
                                                                        InvalidPasswordException,
                                                                        InvalidUsernameException,
                                                                        InvalidEmailException,
                                                                        UnavailableEmailAddressException,
                                                                        UnavailableUsernameException {
        // Arrange
        doThrow(new InvalidEmailException()).when(registrationService).registration(validName, validUsername, invalidEmail, validPassword, "Manager");

        // Act
        String viewName = registrationController.registration(validName, validUsername, invalidEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid email address entered. Please enter a valid email address.");
    }

    @Test
    void testUnsuccessfulRegistrationDueToInvalidName() throws  InvalidNameException,
                                                                InvalidPasswordException,
                                                                InvalidUsernameException,
                                                                InvalidEmailException,
                                                                UnavailableEmailAddressException,
                                                                UnavailableUsernameException {
        // Arrange
        doThrow(new InvalidNameException()).when(registrationService).registration(invalidName, validUsername, validEmail, validPassword, "Manager");

        // Act
        String viewName = registrationController.registration(invalidName, validUsername, validEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid name entered. Please enter a valid name.");
    }

    @Test
    void testUnsuccessfulRegistrationDueToInvalidPassword() throws  InvalidNameException,
                                                                    InvalidPasswordException,
                                                                    InvalidUsernameException,
                                                                    InvalidEmailException,
                                                                    UnavailableEmailAddressException,
                                                                    UnavailableUsernameException {
        // Arrange
        doThrow(new InvalidPasswordException()).when(registrationService).registration(validName, validUsername, validEmail, invalidPassword, "User");

        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, invalidPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid password entered. Please enter a valid password.");
    }

    @Test
    void testUnsuccessfulRegistrationDueToInvalidUsername() throws  InvalidNameException,
                                                                    InvalidPasswordException,
                                                                    InvalidUsernameException,
                                                                    InvalidEmailException,
                                                                    UnavailableEmailAddressException,
                                                                    UnavailableUsernameException {
        // Arrange
        doThrow(new InvalidUsernameException()).when(registrationService).registration(validName, invalidUsername, validEmail, validPassword, "User");

        // Act
        String viewName = registrationController.registration(validName, invalidUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Invalid username entered. Please enter a valid username.");
    }

    @Test
    void testUnsuccessfulRegistrationDueToUnavailableEmailAddress() throws  InvalidNameException,
                                                                            InvalidPasswordException,
                                                                            InvalidUsernameException,
                                                                            InvalidEmailException,
                                                                            UnavailableEmailAddressException,
                                                                            UnavailableUsernameException {
        // Arrange
        doThrow(new UnavailableEmailAddressException()).when(registrationService).registration(validName, validUsername, validEmail, validPassword, "Manager");

        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Email address entered is unavailable. Please enter another email address.");
    }

    @Test
    void testUnsuccessfulRegistrationDueToUnexpectedError() throws Exception {
        // Arrange
        doThrow(new RuntimeException()).when(registrationService).registration(validName, validUsername, validEmail, validPassword, "Manager");

        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, "Manager", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Unexpected error occurred. Please try again later.");
    }

    @Test
    void testUnsuccessfulRegistrationDueToUnexpectedErrorRelatedToRole() throws Exception {
        // Arrange
        doThrow(new RuntimeException()).when(registrationService).registration(validName, validUsername, validEmail, validPassword, "Manager");

        // Act
        String viewName = registrationController.registration(validName, validUsername, validEmail, validPassword, "User", model, redirectAttributes);

        // Assert
        assertEquals("registration", viewName);
        verify(model).addAttribute("error", "Unexpected error occurred. Please try again later.");
    }

}