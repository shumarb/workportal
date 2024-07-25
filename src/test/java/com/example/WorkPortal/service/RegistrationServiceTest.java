/**
 * Unit tests for RegistrationService class.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.model.Manager;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.model.User;
import com.example.WorkPortal.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    String validName;
    String validUsername;
    String validEmail;
    String validPassword;
    String invalidName;
    String invalidUsername;
    String invalidEmail;
    String invalidPassword;

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private RegistrationService registrationService;

    @BeforeEach
    void setUp() {
        validName = "Ali Hassan";
        validUsername = "ali_hassan";
        validEmail = "ali_hassan@icloud.com";
        validPassword = "KM56@";
        invalidName = "ad";
        invalidUsername = "ad";
        invalidEmail = "ad";
        invalidPassword = "3d@";
    }

    @Test
    void registerManager_success() {
        // Act
        this.registrationService.registerPerson(validName, validUsername, validEmail, validPassword, "Manager");

        // Assert
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new Manager()));
        assertTrue(this.registrationService.isUsernameRegistered(validUsername));
    }

    @Test
    void registerUser_success() {
        // Act
        this.registrationService.registerPerson(validName, validUsername, validEmail, validPassword, "User");

        // Assert
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new User()));
        assertTrue(this.registrationService.isUsernameRegistered(validUsername));
    }

    @Test
    void isEmailAddressRegistered() {
    }

    @Test
    void isUsernameRegistered() {
    }

    @Test
    void isValidEmailAddress() {
    }

    @Test
    void isValidName() {
    }

    @Test
    void isAllLetters() {
    }

    @Test
    void isValidPassword() {
    }

    @Test
    void isValidUsername() {
    }

    @Test
    void isPasswordRegistered() {
    }

    @Test
    void doesWordHaveAtLeastThreeCharacters() {
    }
}