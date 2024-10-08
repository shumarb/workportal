/**
 * Unit tests for RegistrationService class.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.exceptions.InvalidNameException;
import com.example.WorkPortal.exceptions.InvalidPasswordException;
import com.example.WorkPortal.exceptions.InvalidUsernameException;
import com.example.WorkPortal.exceptions.InvalidEmailException;
import com.example.WorkPortal.exceptions.UnavailableEmailAddressException;
import com.example.WorkPortal.exceptions.UnavailableUsernameException;
import com.example.WorkPortal.model.Manager;
import com.example.WorkPortal.model.User;
import com.example.WorkPortal.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private RegistrationService registrationService;

    boolean hasAtLeastThreeCharacters;
    boolean isAllLetters;
    boolean isEmailAddressRegistered;
    boolean isValidEmailAddress;
    boolean isValidName;
    boolean isValidPassword;
    boolean isValidUsername;
    boolean isUsernameRegistered;
    String validName;
    String validUsername;
    String validEmail;
    String validPassword;
    String invalidName;
    String invalidUsername;
    String invalidEmail;
    String invalidPassword;

    @BeforeEach
    void setUp() {
        // Arrange
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
    void testSuccessfulRegistrationForManager() throws  InvalidNameException,
                                                        InvalidPasswordException,
                                                        InvalidUsernameException,
                                                        InvalidEmailException,
                                                        UnavailableEmailAddressException,
                                                        UnavailableUsernameException {
        // Act
        this.registrationService.registration(validName, validUsername, validEmail, validPassword, "Manager");
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new Manager()));

        // Assert
        assertTrue(this.registrationService.isUsernameRegistered(validUsername));
    }

    @Test
    void testSuccessfulRegistrationForUser() throws InvalidNameException,
                                                    InvalidPasswordException,
                                                    InvalidUsernameException,
                                                    InvalidEmailException,
                                                    UnavailableEmailAddressException,
                                                    UnavailableUsernameException {
        // Act
        this.registrationService.registration(validName, validUsername, validEmail, validPassword, "User");
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new User()));

        // Assert
        assertTrue(this.registrationService.isUsernameRegistered(validUsername));
    }

    @Test
    void testIsEmailAddressRegisteredReturnsTrueForRegisteredEmailAddress() {
        // Act
        lenient().when(this.personRepository.findByEmail(validEmail)).thenReturn(Optional.of(new User()));
        isEmailAddressRegistered = this.registrationService.isEmailAddressRegistered(validEmail);

        // Assert
        assertTrue(isEmailAddressRegistered);
    }

    @Test
    void testIsEmailAddressRegisteredReturnsFalseForUnregisteredEmailAddress() {
        // Act
        lenient().when(this.personRepository.findByEmail(validEmail)).thenReturn(Optional.empty());
        isEmailAddressRegistered = this.registrationService.isEmailAddressRegistered(validEmail);

        // Assert
        assertFalse(isEmailAddressRegistered);
    }

    @Test
    void testIsUsernameRegisteredReturnsTrueForRegisteredUsername() {
        // Act
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new Manager()));
        isUsernameRegistered = this.registrationService.isUsernameRegistered(validUsername);

        // Assert
        assertTrue(isUsernameRegistered);
    }

    @Test
    void testIsUsernameRegisteredReturnsFalseForUnregisteredUsername() {
        // Act
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.empty());
        boolean isUserNameRegistered = this.registrationService.isUsernameRegistered(validUsername);

        // Assert
        assertFalse(isUserNameRegistered);
    }

    @Test
    void testIsValidEmailAddressReturnsTrueForValidEmailAddress() {
        // Act
        isValidEmailAddress = this.registrationService.isValidEmailAddress(validEmail);

        // Assert
        assertTrue(isValidEmailAddress);
    }

    @Test
    void testIsValidEmailAddressForInvalidEmailAddress() {
        // Act
        isValidEmailAddress = this.registrationService.isValidEmailAddress(invalidEmail);

        // Assert
        assertFalse(isValidEmailAddress);
    }

    @Test
    void testIsValidNameReturnsTrueForValidName() {
        // Act
        isValidName = this.registrationService.isValidName(validName);

        // Assert
        assertTrue(isValidName);
    }

    @Test
    void testIsValidNameReturnsTrueForInvalidName() {
        // Act
        isValidName = this.registrationService.isValidName(invalidName);

        // Assert
        assertFalse(isValidName);
    }

    @Test
    void testIsAllLettersReturnsTrueForWordContainingOnlyLetters() {
        // Act
        boolean isAllLetters = this.registrationService.isAllLetters("word");

        // Assert
        assertTrue(isAllLetters);
    }

    @Test
    void testIsAllLettersReturnsFalseForWordNotContainingOnlyLetters() {
        // Act
        isAllLetters = this.registrationService.isAllLetters(validUsername);

        // Assert
        assertFalse(isAllLetters);
    }

    @Test
    void testIsValidPasswordReturnsTrueForValidPassword() {
        // Act
        boolean isValidPassword = this.registrationService.isValidPassword(validPassword);

        // Assert
        assertTrue(isValidPassword);
    }

    @Test
    void testIsValidPasswordReturnsFalseForInvalidPassword() {
        // Act
        isValidPassword = this.registrationService.isValidPassword(invalidPassword);

        // Assert
        assertFalse(isValidPassword);
    }

    @Test
    void testIsValidUsernameReturnsTrueForValidUsername() {
        // Act
        isValidUsername = this.registrationService.isValidUsername(validUsername);

        // Assert
        assertTrue(isValidUsername);
    }

    @Test
    void testIsValidUsernameReturnsFalseForInvalidUsername() {
        // Act
        isValidUsername = this.registrationService.isValidUsername(invalidUsername);

        // Assert
        assertFalse(isValidUsername);
    }

    @Test
    void testDoesWordHaveAtLeastThreeCharactersReturnsTrueForWordContainingAtLeastThreeCharacters() {
        // Act
        hasAtLeastThreeCharacters = this.registrationService.isValidUsername(validUsername);

        // Assert
        assertTrue(hasAtLeastThreeCharacters);
    }

    @Test
    void testDoesWordHaveAtLeastThreeCharactersReturnsFalseForWordContainingLessThanThreeCharacters() {
        // Act
        hasAtLeastThreeCharacters = this.registrationService.isValidUsername(invalidUsername);

        // Assert
        assertFalse(hasAtLeastThreeCharacters);
    }

}
