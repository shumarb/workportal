/**
 * Class of a Manager entity in the application that extends the {@link Person} class.
 * This is for a Person entity whose role is a Manager.
 * This class is annotated with JPA annotations and mapped to the MySQL database table called "manager".
 */

package com.example.WorkPortal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
@PrimaryKeyJoinColumn(name = "manager_id")
public class Manager extends Person {

    /**
     * Default constructor required by Hibernate.
     */
    public Manager() {

    }

    /**
     * Constructs a Manager object with the specified name, username, email, and password.
     * The role is set to "Manager" by default.
     * @param name      The name of the Manager.
     * @param username  The username of the Manager.
     * @param email     The email address of the Manager.
     * @param password  The password of the Manager.
     */
    public Manager(String name, String username, String email, String password) {
        super(name, username, email, password, "Manager");
    }

}
