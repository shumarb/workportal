/**
 * Unit tests for RegistrationService class.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.exceptions.*;
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
    void test_registerManager_success() throws InvalidNameException, UnavailablePasswordException, UnavailableEmailAddressException, InvalidPasswordException, InvalidUsernameException, InvalidEmailException, UnavailableUsernameException {
        // Act
        this.registrationService.registerPerson(validName, validUsername, validEmail, validPassword, "Manager");
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new Manager()));

        // Assert
        assertTrue(this.registrationService.isUsernameRegistered(validUsername));
    }

    @Test
    void test_registerUser_success() throws InvalidNameException, UnavailablePasswordException, UnavailableEmailAddressException, InvalidPasswordException, InvalidUsernameException, InvalidEmailException, UnavailableUsernameException {
        // Act
        this.registrationService.registerPerson(validName, validUsername, validEmail, validPassword, "User");
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new User()));

        // Assert
        assertTrue(this.registrationService.isUsernameRegistered(validUsername));
    }

    @Test
    void test_isEmailAddressRegistered_registered() {
        // Act
        lenient().when(this.personRepository.findByEmail(validEmail)).thenReturn(Optional.of(new User()));
        boolean isEmailAddressRegistered = this.registrationService.isEmailAddressRegistered(validEmail);

        // Assert
        assertTrue(isEmailAddressRegistered);
    }

    @Test
    void test_isEmailAddressRegistered_notRegistered() {
        // Act
        lenient().when(this.personRepository.findByEmail(validEmail)).thenReturn(Optional.empty());
        boolean isEmailAddressRegistered = this.registrationService.isEmailAddressRegistered(validEmail);

        // Assert
        assertFalse(isEmailAddressRegistered);
    }

    @Test
    void test_isUsernameRegistered_registered() {
        // Act
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(new Manager()));
        boolean isUserNameRegistered = this.registrationService.isUsernameRegistered(validUsername);

        // Assert
        assertTrue(isUserNameRegistered);
    }

    @Test
    void test_isUsernameRegistered_notRegistered() {
        // Act
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.empty());
        boolean isUserNameRegistered = this.registrationService.isUsernameRegistered(validUsername);

        // Assert
        assertFalse(isUserNameRegistered);
    }

    @Test
    void test_isValidEmailAddress_validEmailAddressFormat() {
        // Act
        boolean isValidEmailAddress = this.registrationService.isValidEmailAddress(validEmail);

        // Assert
        assertTrue(isValidEmailAddress);
    }

    @Test
    void test_isValidEmailAddress_invalidEmailAddressFormat() {
        // Act
        boolean isValidEmailAddress = this.registrationService.isValidEmailAddress(invalidEmail);

        // Assert
        assertFalse(isValidEmailAddress);
    }

    @Test
    void isValidName_validNameFormat() {
        // Act
        boolean isValidName = this.registrationService.isValidName(validName);

        // Assert
        assertTrue(isValidName);
    }

    @Test
    void isValidName_invalidNameFormat() {
        // Act
        boolean isValidName = this.registrationService.isValidName(invalidName);

        // Assert
        assertFalse(isValidName);
    }

    @Test
    void isAllLetters_valid() {
        // Arrange
        String word = "word";

        // Act
        boolean isAllLetters = this.registrationService.isAllLetters(word);

        // Assert
        assertTrue(isAllLetters);
    }

    @Test
    void isAllLetters_invalid() {
        // Act
        boolean isAllLetters = this.registrationService.isAllLetters(validUsername);

        // Assert
        assertFalse(isAllLetters);
    }

    @Test
    void isValidPassword_validPassword() {
        // Act
        boolean isValidPassword = this.registrationService.isValidPassword(validPassword);

        // Assert
        assertTrue(isValidPassword);
    }

    @Test
    void isValidPassword_invalidPassword() {
        // Act
        boolean isValidPassword = this.registrationService.isValidPassword(invalidPassword);

        // Assert
        assertFalse(isValidPassword);
    }

    @Test
    void isValidUsername_valid() {
        // Act
        boolean isValidUsername = this.registrationService.isValidUsername(validUsername);

        // Assert
        assertTrue(isValidUsername);
    }

    @Test
    void isValidUsername_invalid() {
        // Act
        boolean isValidUsername = this.registrationService.isValidUsername(invalidUsername);

        // Assert
        assertFalse(isValidUsername);
    }

    @Test
    void isPasswordRegistered_registered() throws InvalidNameException, UnavailablePasswordException, UnavailableEmailAddressException, InvalidPasswordException, InvalidUsernameException, InvalidEmailException, UnavailableUsernameException {
        // Act
        this.registrationService.registerPerson(validName, validUsername, validEmail, validPassword, "Manager");
        lenient().when(this.personRepository.findByPassword(validPassword)).thenReturn(Optional.of(new Manager()));

        // Assert
        assertTrue(this.registrationService.isPasswordRegistered(validPassword));
    }

    @Test
    void isPasswordRegistered_notRegistered() {
        // Act
        lenient().when(this.personRepository.findByPassword(validPassword)).thenReturn((Optional.empty()));

        // Assert
        assertFalse(this.registrationService.isPasswordRegistered(validPassword));
    }

    @Test
    void doesWordHaveAtLeastThreeCharacters_valid() {
        // Act
        boolean hasAtLeastThreeCharacters = this.registrationService.isValidUsername(validUsername);

        // Assert
        assertTrue(hasAtLeastThreeCharacters);
    }

    @Test
    void doesWordHaveAtLeastThreeCharacters_invalid() {
        // Act
        boolean hasAtLeastThreeCharacters = this.registrationService.isValidUsername(invalidUsername);

        // Assert
        assertFalse(hasAtLeastThreeCharacters);
    }

}
