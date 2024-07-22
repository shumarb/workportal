/**
 * Service class for managing login operations of Person entities.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.exceptions.InvalidUsernameOrPasswordException;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    /**
     * Logger to monitor operations flow and assist in troubleshooting for Login operation.
     */
    private static final Logger loginServiceLogger = LogManager.getLogger(LoginService.class);

    /**
     * Repository interface for performing CRUD operations on Person entities.
     */
    private final PersonRepository personRepository;

    /**
     * Constructs a LoginService instance with the specified PersonRepository.
     *
     * @param personRepository The repository of Person entities.
     */
    @Autowired
    public LoginService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Attempts to log in a Person entity with provided username and password.
     *
     * @param username The username of a Person entity to be logged in.
     * @param password The password of a Person entity to be logged in.
     * @return Person entity if login is successful,
     * @throws InvalidUsernameOrPasswordException for incorrect username or password.
     */
    public Person login(String username, String password) throws InvalidUsernameOrPasswordException {
        loginServiceLogger.info("LoginServiceLogger: Currently at loginByCredentials method. Username: {}, Password: {}", username, password);
        Optional<Person> personOptional= this.personRepository.findByUsername(username);
        if (personOptional.isPresent() && personOptional.get().getPassword().equals(password)) {
            loginServiceLogger.info("LoginServiceLogger: Successful Login. Username and Password matches with {}.", personOptional.toString());
            return personOptional.get();
        }
        loginServiceLogger.error("loginServiceLogger: Invalid Username or Password.");
        throw new InvalidUsernameOrPasswordException();
    }

}
