/**
 * Repository interface for managing Person entity.
 */

package com.example.WorkPortal.repository;

import com.example.WorkPortal.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Retrieves a Person by email
     */
    Optional<Person> findByEmail(String email);

    /**
     * Retrieves a Person by username
     */
    Optional<Person> findByUsername(String username);
}
