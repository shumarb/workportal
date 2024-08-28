/**
 * Service class for managing registration operations of Person entities.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.exceptions.*;
import com.example.WorkPortal.model.Manager;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.model.User;
import com.example.WorkPortal.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegistrationService {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Registration operation.
     */
    private static final Logger logger = LogManager.getLogger(RegistrationService.class);

   /**
     * Repository interface for performing CRUD operations on Person entities.
     */
    private final PersonRepository personRepository;

    /**
     * Constructs a RegistrationService instance with the specified PersonRepository.
     *
     * @param personRepository the repository for Person entities.
     */
    @Autowired
    public RegistrationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Registers a Person entity in the application.
     *
     * @param name      The name of the Person.
     * @param username  The username of the Person.
     * @param email     The email address of the Person.
     * @param password  The password of the Person.
     * @param role      The role of the Person.
     */
    public Person registration(String name,
                             String username,
                             String email,
                             String password,
                             String role) throws    InvalidNameException,
                                                    InvalidUsernameException,
                                                    InvalidEmailException,
                                                    InvalidPasswordException,
                                                    UnavailableUsernameException,
                                                    UnavailableEmailAddressException {
        logger.info("Currently at registration method. Name: {}, Username: {}, Email: {}, Password: {}, Role: {}", name, username, email, password, role);
        Person person = null;

        if (!isValidName(name)) {
            throw new InvalidNameException();
        }

        if (!isValidUsername(username)) {
            throw new InvalidUsernameException();
        }

        if (!isValidEmailAddress(email)) {
            throw new InvalidEmailException();
        }

        if (!isValidPassword(password)) {
            throw new InvalidPasswordException();
        }

        if (isUsernameRegistered(username)) {
            throw new UnavailableUsernameException();
        }

        if (isEmailAddressRegistered(email)) {
            throw new UnavailableEmailAddressException();
        }

        if (role.equals("User")) {
            person = new User(name, username, email, password);
            this.personRepository.save(person);
        }

        if (role.equals("Manager")) {
            person = new Manager(name, username, email, password);
            this.personRepository.save(person);
        }

        if (person != null) {
            logger.info("Successful registration of {}", person.toString());
        }

        return person;
    }

    /**
     * Checks if a Person entity's email address has been registered earlier.
     *
     * @param email         The email address of the Person entity.
     * @return              {@code true} if email has been registered, {@code false} otherwise.
     */
    public boolean isEmailAddressRegistered(String email) {
        logger.info("Currently at isEmailAddressRegistered method. Email: {}", email);
        return this.personRepository.findByEmail(email).isPresent();
    }

    /**
     * Checks if a Person entity's username has been registered earlier.
     *
     * @param username  The username of the Person entity.
     * @return          {@code true} if username has been registered, {@code false} otherwise.
     */
    public boolean isUsernameRegistered(String username) {
        logger.info("Currently at isUsernameRegistered method. Email: {}", username);
        return this.personRepository.findByUsername(username).isPresent();
    }

    /**
     * Checks if a Person entity's email is in valid format.
     *
     * @param email The email address of the Person entity.
     * @return      {@code true} if valid email address, {@code false} otherwise.
     */
    public boolean isValidEmailAddress(String email) {
        logger.info("Currently at isValidEmailAddress method. Email: {}", email);
        // Regular expression pattern for an email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        // Compile pattern and create a Matcher instance
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    /**
     * Checks if a Person entity's name is in valid format.
     *
     * @param name  The name of the Person entity.
     * @return      {@code true} if valid name format, {@code false} otherwise.
     */
    public boolean isValidName(String name) {
        logger.info("Currently at isValidName method. Name: {}", name);

        // Split in this way in case of two or more consecutive spaces.
        String[] words = name.trim().split("\\s+");

        // Ensure a name has exactly 2 words.
        if (words.length != 2) {
            return false;
        }

        // Ensure a name contains only letters, and each word in letter has at least three characters
        for (String word: words) {
            if (!isAllLetters(word) || !doesWordHaveAtLeastThreeCharacters(word)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a word contains only letters.
     *
     * @param word  The word to be checked.
     * @return      {@code true} if word has only letters, {@code false} otherwise.
     */
    public boolean isAllLetters(String word) {
        logger.info("Currently at isAllLetters method. Word: {}", word);
        return word.matches("[a-zA-Z]+");
    }

    /**
     * Checks if a password is valid.
     *
     * @param password  The password of the Person entity.
     * @return          {@code true} if the password is valid, {@code false} otherwise.
     */
    public boolean isValidPassword(String password) {
        logger.info("Currently at isValidPassword method. Password: {}", password);
        // Password has at least 5 characters.
        if (password.length() < 5) {
            return false;
        }

        // Password has at least two letters, at least two numbers, and at least one special character
        int letterCount = 0;
        int digitCount = 0;
        int specialCharacterCount = 0;
        for (char ch: password.toCharArray()) {
            if (Character.isDigit(ch)) {
                digitCount++;
            }
            if (Character.isLetter(ch)) {
                letterCount++;
            }
            if (!Character.isLetterOrDigit(ch)) {
                specialCharacterCount++;
            }
        }

        if (letterCount < 2) {
            logger.error("Invalid password due to number of letters in password: {}", letterCount);
        } else if (digitCount < 2) {
            logger.error("Invalid password due to number of digits in password: {}", digitCount);
        } else if (specialCharacterCount < 2) {
            logger.error("Invalid password due to number of special characters in password: {}", specialCharacterCount);
        } else {
            logger.info("Valid password");
        }

        return letterCount >= 2 && digitCount >= 2 && specialCharacterCount >= 1;
    }

    /**
     * Checks that username is valid.
     *
     * @param username  The username of the Person entity.
     * @return          {@code true} is username is valid, {@code false} otherwise.
     */
    public boolean isValidUsername(String username) {
        logger.info("Currently at isValidUsername method. Username: {}", username);
        boolean isUsernameLengthAtLeastFive = username.length() >= 5;
        boolean doesUsernameNotContainSpace = username.contains(" ");

        if (!isUsernameLengthAtLeastFive) {
            logger.error("Invalid username as its length is less than 5.");
        }
        if (!doesUsernameNotContainSpace) {
            logger.error("Invalid username as it contains a space.");
        }

        if (isUsernameLengthAtLeastFive && doesUsernameNotContainSpace) {
            logger.info("Valid username.");
            return true;
        }
        return false;
    }

    /**
     * Checks if a word has at least 2 characters.
     *
     * @param word      The word to be checked.
     * @return          {@code true} if the word has at least 2 characters, {@code false} otherwise.
     */
    public boolean doesWordHaveAtLeastThreeCharacters(String word) {
        logger.info("Currently at doesWordHaveAtLeastThreeCharacters method. Username: {}", word);
        boolean result = word.length() >= 3;
        if (result) {
            logger.info("Word has at least 3 words.");
        } else {
            logger.error("Word has less than 3 words.");
        }
        return result;
    }

}
