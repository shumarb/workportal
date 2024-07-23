/**
 * Service class for managing registration operations of Person entities.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.model.Manager;
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
    private static final Logger registrationServiceLogger = LogManager.getLogger(RegistrationService.class);

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
    public void registerPerson(String name,
                               String username,
                               String email,
                               String password,
                               String role) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at registerPerson method. Person entity created. " +
                                        "Name: {}, Username: {}, Email: {}, Password: {}, Role: {}",
                                         name, username, email, password, role);
        if (role.equals("User")) {
            this.personRepository.save(new User(name, username, email, password));
        }

        if (role.equals("Manager")) {
            this.personRepository.save(new Manager(name, username, email, password));
        }

    }

    /**
     * Checks if a Person entity's email address has been registered earlier.
     *
     * @param email The email address of the Person entity.
     * @return {@code true} if email has been registered, {@code false} otherwise.
     */
    public boolean isEmailAddressRegistered(String email) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at isEmailAddressRegistered method. Email: {}", email);
        return this.personRepository.findByEmail(email).isPresent();
    }

    /**
     * Checks if a Person entity's username has been registered earlier.
     *
     * @param username The username of the Person entity.
     * @return {@code true} if username has been registered, {@code false} otherwise.
     */
    public boolean isUsernameRegistered(String username) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at isUsernameRegistered method. Email: {}", username);
        return this.personRepository.findByUsername(username).isPresent();
    }

    /**
     * Checks if a Person entity's email is in valid format.
     *
     * @param email The email address of the Person entity.
     * @return {@code true} if valid email address, {@code false} otherwise.
     */
    public boolean isValidEmailAddress(String email) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at isValidEmailAddress method. Email: {}", email);
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
     * @param name The name of the Person entity.
     * @return {@code true} if valid name format, {@code false} otherwise.
     */
    public boolean isValidName(String name) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at isValidName method. Name: {}", name);

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
     * @param word The word to be checked.
     * @return {@code true} if word has only letters, {@code false} otherwise.
     */
    public boolean isAllLetters(String word) {
        registrationServiceLogger.info("Currently at isAllLetters method. Word: {}", word);
        return word.matches("[a-zA-Z]+");
    }

    /**
     * Checks if a password is valid.
     *
     * @param password The password of the Person entity.
     * @return {@code true} if the password is valid, {@code false} otherwise.
     */
    public boolean isValidPassword(String password) {
        registrationServiceLogger.info("Currently at isValidPassword method. Password: {}", password);
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

        return letterCount >= 2 && digitCount >= 2 && specialCharacterCount >= 1;
    }

    /**
     * Checks that username is valid.
     *
     * @param username The username of the Person entity.
     * @return {@code true} is username is valid, {@code false} otherwise.
     */
    public boolean isValidUsername(String username) {
        registrationServiceLogger.info("Currently at isValidUsername method. Username: {}", username);
        return username.length() >= 5 && !username.contains(" ");
    }

    /**
     * Checks that password has been registered with another Person entity.
     *
     * @param password The password of the Person entity.
     * @return {@code true} if password has been registered to a Person entity earlier, {@code false} otherwise.
     */
    public boolean isPasswordRegistered(String password) {
        registrationServiceLogger.info("Currently at isPasswordRegistered method. Username: {}", password);
        return this.personRepository.findByPassword(password).isPresent();
    }

    /**
     * Checks if a word has at least 2 characters.
     *
     * @param word the word to be checked.
     * @return true if the word has at least 2 characters, false otherwise.
     */
    public boolean doesWordHaveAtLeastThreeCharacters(String word) {
        registrationServiceLogger.info("Currently at doesWordHaveAtLeastThreeCharacters method. Username: {}", word);
        return word.length() >= 3;
    }

}
