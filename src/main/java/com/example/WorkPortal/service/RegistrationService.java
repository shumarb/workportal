/**
 * Service class for managing registration of Person entities.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.model.Person;
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
     * Logger to monitor operational flow and assist in troubleshooting for Registration operations.
     */
    private static final Logger registrationServiceLogger = LogManager.getLogger(RegistrationService.class);

   /**
     * Repository interface for performing CRUD operations on Person entities.
     */
    private final PersonRepository personRepository;

    /**
     * Instantiates a PersonService instance with the provided PersonRepository.
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
     * @param name the name of the Person
     * @param username the username of the Person
     * @param email the email address of the Person
     * @param password the password of the Person
     * @param role the role of the Person
     */
    public void registerPerson(String name,
                               String username,
                               String email,
                               String password,
                               String role) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at registerPerson method. Person entity created. " +
                                        "Name: {}, Username: {}, Email: {}, Password: {}, Role: {}",
                                         name, username, email, password, role);
        this.personRepository.save(new Person(name, username, email, password, role));
    }

    /**
     * Checks if a Person entity's email address has been registered earlier.
     *
     * @param email the email address of the Person entity.
     * @return true if email has been registered, false otherwise.
     */
    public boolean isEmailAddressRegistered(String email) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at isEmailAddressRegistered method. Email: {}", email);
        return this.personRepository.findByEmail(email).isPresent();
    }

    /**
     * Checks if a Person entity's username has been registered earlier.
     *
     * @param username the username of the Person entity.
     * @return true if username has been registered, false otherwise.
     */
    public boolean isUsernameRegistered(String username) {
        registrationServiceLogger.info("RegistrationServiceLogger: Currently at isUsernameRegistered method. Email: {}", username);
        return this.personRepository.findByUsername(username).isPresent();
    }

    /**
     * Checks if a Person entity's email is in valid format.
     *
     * @param email the email address of the Person entity.
     * @return true if valid email address, false otherwise.
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
     * @param name the name of the Person entity.
     * @return true if valid name format, false otherwise.
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
     * @param word the word to be checked.
     * @return true if word has only letters, false otherwise.
     */
    public boolean isAllLetters(String word) {
        registrationServiceLogger.info("Currently at isAllLetters method. Word: {}", word);
        return word.matches("[a-zA-Z]+");
    }

    /**
     * Checks if a password is valid.
     *
     * @param password the password of the Person entity.
     * @return true if the password is valid, false otherwise.
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
     * @param username the username of the Person entity.
     * @return true is username is valid, false otherwise.
     */
    public boolean isValidUsername(String username) {
        registrationServiceLogger.info("Currently at isValidUsername method. Username: {}", username);
        return username.length() >= 5 && !username.contains(" ");
    }

    /**
     * Checks that password has been registered with another Person entity.
     *
     * @param password the password of the Person entity.
     * @return true if password has been registered to a Person entity earlier, false otherwise.
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
