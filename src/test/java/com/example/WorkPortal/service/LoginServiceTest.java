package com.example.WorkPortal.service;

import com.example.WorkPortal.exceptions.InvalidUsernameOrPasswordException;
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
import static org.mockito.Mockito.when;

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
    void test_loginSuccess() throws InvalidUsernameOrPasswordException {
        // Arrange
        Person manager = new Manager(validName, validUsername, validEmail, validPassword);

        // Act
        lenient().when(this.personRepository.findByUsername(validUsername)).thenReturn(Optional.of(manager));

        // Assert
        assertEquals(validUsername, manager.getUsername());
    }


    @Test
    void test_loginFailure_validUsernameAndInvalidPassword() {
        // Arrange
        Person user = new User(validName, validUsername, validEmail, validPassword);

        // Act
        lenient().when(this.personRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        // Assert
        assertThrows(InvalidUsernameOrPasswordException.class, () -> {
            this.loginService.login(validUsername, invalidPassword);
        });
    }

    @Test
    void test_loginFailure_invalidUsernameAndValidPassword() {
        // Arrange and act
        lenient().when(this.personRepository.findByUsername(invalidUsername)).thenReturn(Optional.empty());

        // Assert
        assertThrows(InvalidUsernameOrPasswordException.class, () -> {
            this.loginService.login(invalidUsername, validPassword);
        });
    }

    @Test
    void test_loginFailure_invalidUsernameAndInvalidPassword() {
        // Arrange and act
        lenient().when(this.personRepository.findByUsername(invalidUsername)).thenReturn(Optional.empty());

        // Assert
        assertThrows(InvalidUsernameOrPasswordException.class, () -> {
            this.loginService.login(invalidUsername, invalidPassword);
        });
    }

}
