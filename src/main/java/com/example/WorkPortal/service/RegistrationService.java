/**
 * Service class for managing registration of Person entities.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.exceptions.UnavailableEmailAddressAndUsernameException;
import com.example.WorkPortal.exceptions.UnavailableEmailAddressException;
import com.example.WorkPortal.exceptions.UnavailableUsernameException;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final PersonRepository personRepository;

    /**
     * Instantiates a PersonService instance with the provided PersonRepository.
     * @param personRepository the repository for Person entities.
     */
    @Autowired
    public RegistrationService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Registers a Person entity in the application..
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
        this.personRepository.save(new Person(name, username, email, password, role));
    }

    /**
     * Checks if a Person entity's email has been registered earlier.
     *
     * @return true if email has been registered, otherwise return false.
     */
    public boolean isEmailAddressRegistered(String email) {
        return this.personRepository.findByEmail(email).isPresent();
    }

    /**
     * Checks if a Person entity's username has been registered earlier.
     *
     * @return true if username has been registered, otherwise return false.
     */
    public boolean isUsernameRegistered(String username) {
        return this.personRepository.findByUsername(username).isPresent();
    }

}
