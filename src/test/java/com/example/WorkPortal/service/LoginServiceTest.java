/**
 * Unit tests for LoginService class.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.exceptions.UnsuccessfulLoginException;
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
class LoginServiceTest {

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
    private LoginService loginService;

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
    void testLoginSuccess() {
        // Arrange
        Person manager = new Manager(validName, validUsername, validEmail, validPassword);

        // Act
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(manager));

        // Assert
        assertEquals(validUsername, manager.getUsername());
    }


    @Test
    void testLoginFailureDueToValidUsernameAndInvalidPassword() {
        // Arrange
        Person user = new User(validName, validUsername, validEmail, validPassword);

        // Act
        lenient().when(this.personRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        // Assert
        assertThrows(UnsuccessfulLoginException.class, () -> {
            this.loginService.login(validUsername, invalidPassword);
        });
    }

    @Test
    void testLoginFailureDueToInvalidUsernameAndValidPassword() {
        // Arrange and act
        lenient().when(this.personRepository.findByUsername(invalidUsername)).thenReturn(Optional.empty());

        // Assert
        assertThrows(UnsuccessfulLoginException.class, () -> {
            this.loginService.login(invalidUsername, validPassword);
        });
    }

    @Test
    void testLoginFailureDueToInvalidUsernameAndInvalidPassword() {
        // Arrange and act
        lenient().when(this.personRepository.findByUsername(invalidUsername)).thenReturn(Optional.empty());

        // Assert
        assertThrows(UnsuccessfulLoginException.class, () -> {
            this.loginService.login(invalidUsername, invalidPassword);
        });
    }

    @Test
    void testLoginFailureDueToNullUsernameAndValidPassword() {
        // Arrange and act
        lenient().when(this.personRepository.findByUsername(null)).thenReturn(Optional.empty());

        // Assert
        assertThrows(UnsuccessfulLoginException.class, () -> {
            this.loginService.login(null, validPassword);
        });
    }

    @Test
    void testLoginFailureDueToNullUsernameAndInvalidPassword() {
        // Arrange and act
        lenient().when(this.personRepository.findByUsername(null)).thenReturn(Optional.empty());

        // Assert
        assertThrows(UnsuccessfulLoginException.class, () -> {
            this.loginService.login(null, invalidPassword);
        });
    }

    @Test
    void testLoginFailureDueToValidUsernameAndNullPassword() {
        // Arrange
        Person manager = new Manager(validName, validUsername, validEmail, validPassword);

        // Act
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(manager));

        // Assert
        assertThrows(UnsuccessfulLoginException.class, () -> {
            this.loginService.login(validUsername, null);
        });
    }

    @Test
    void testLoginFailureDueToInvalidUsernameAndNullPassword() {
        // Arrange and act
        lenient().when(this.personRepository.findByUsername(null)).thenReturn(Optional.empty());

        // Assert
        assertThrows(UnsuccessfulLoginException.class, () -> {
            this.loginService.login(invalidUsername, null);
        });
    }

}
