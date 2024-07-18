/**
 * Service class for managing operations related to Person entities.
 * Service class serves as intermediary between PersonController and PersonRepository.
 */

package com.example.WorkPortal.service;

import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    /**
     * Instantiates a PersonService instance with the provided PersonRepository.
     * @param personRepository the repository for Person entities.
     */
    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    /**
     * Registers a Person into the database by saving the Person entity.
     * @param person the Person entity to be saved.
     */
    public void registerPerson(Person person) {
        this.personRepository.save(person);
    }
}
