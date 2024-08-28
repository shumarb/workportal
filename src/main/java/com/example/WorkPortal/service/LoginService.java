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
    private static final Logger logger = LogManager.getLogger(LoginService.class);

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
     * @param username                              The username of a Person entity to be logged in.
     * @param password                              The password of a Person entity to be logged in.
     * @return                                      Person entity for successful login.
     * @throws InvalidUsernameOrPasswordException   for incorrect username or password.
     */
    public Person login(String username, String password) throws InvalidUsernameOrPasswordException {
        logger.info("Currently at login method. Username: {}, Password: {}", username, password);
        Optional<Person> personOptional= this.personRepository.findByUsername(username);
        if (personOptional.isPresent() && personOptional.get().getPassword().equals(password)) {
            logger.info("Successful Login. Username and Password matches with {}.", personOptional.toString());
            return personOptional.get();
        }
        logger.error("Invalid username or password.");
        throw new InvalidUsernameOrPasswordException();
    }

}
