/**
 * Repository interface for managing Person entity.
 */

package com.example.WorkPortal.repository;

import com.example.WorkPortal.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Retrieves a Person entity by email.
     *
     * @param email the email address of the Person entity.
     * @return an Optional<Person> containing the Person entity if found, or empty if not found.
     */
    Optional<Person> findByEmail(String email);

    /**
     * Retrieves a Person entity by password.
     *
     * @param password the password of the Person entity.
     * @return an Optional<Person> containing the Person entity if found, or empty if not found.
     */
    Optional<Person> findByPassword(String password);

    /**
     * Retrieves a Person entity by username.
     *
     * @param username the username of the Person entity.
     * @return an Optional<Person> containing the Person entity if found, or empty if not found.
     */
    Optional<Person> findByUsername(String username);
    
}
