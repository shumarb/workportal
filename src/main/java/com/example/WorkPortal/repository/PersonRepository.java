/**
 * Repository interface for managing Person entity.
 */

package com.example.WorkPortal.repository;

import com.example.WorkPortal.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
