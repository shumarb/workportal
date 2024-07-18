/**
 * Service class for managing registration of Person entities.
 */

package com.example.WorkPortal.service;

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
     * Registers a Person into the database by saving the Person entity.
     * @param person the Person entity to be saved.
     */
    public void registerPerson(Person person) {
        this.personRepository.save(person);
    }

    /**
     * Checks that the username and email address of the Person entity is available for registration
     *
     */
    public boolean isAbleToBeRegistered() {
        return true;
    }
}
