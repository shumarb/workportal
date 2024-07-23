/**
 * Class of a User entity in the application that extends the {@link Person} class.
 * This is for a Person entity whose role is a User.
 * This class is annotated with JPA annotations and mapped to the MySQL database table called "user".
 */

package com.example.WorkPortal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Person {

    /**
     * Default constructor required by Hibernate.
     */
    public User() {

    }

    /**
     * Constructs a User object with the specified name, username, email, and password.
     * The role is set to "User" by default.
     * @param name      The name of the User.
     * @param username  The username of the User.
     * @param email     The email address of the User.
     * @param password  The password of the User.
     */
    public User(String name, String username, String email, String password) {
        super(name, username, email, password, "User");
    }
}
