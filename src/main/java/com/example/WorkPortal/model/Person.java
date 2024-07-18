/**
 * Class for a Person entity in the application.
 */

package com.example.WorkPortal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {

    /**
     * Identification number of a Person.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of a Person.
     */
    private String name;

    /**
     * Username of a Person.
     */
    @Column(unique = true)
    private String username;

    /**
     * Email address of a Person.
     */
    @Column(unique = true)
    private String email;

    /**
     * Password of a Person.
     */
    @Column(unique = true)
    private String password;

    /**
     * Role of a Person (either User or Manager).
     */
    private String role;

    /**
     * Constructs a new Person with the given name, username, email, password, and role.
     *
     * @param name     The name of the Person object.
     * @param username The unique username of the Person object used for login.
     * @param email    The email address of the Person object.
     * @param password The password for the Person object.
     * @param role     The role for the Person object.
     */
    public Person(String name, String username, String email, String password, String role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Retrieves the unique identifier of the user profile.
     *
     * @return The ID of the user profile.
     */
    public long getId() {
        return id;
    }

    /**
     * Retrieves the full name of the Person object.
     *
     * @return The full name of the Person object.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the Person object.
     *
     * @param name The full name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unique username of the Person object used for login.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the unique username used by Person object for login.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email address associated with the Person object.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address associated with the Person object.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password for the Person object.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the Person object.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the role for the Person object.
     *
     * @return The role.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the password for the Person object.
     *
     * @param role The role to set.
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Person {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", username = '" + username + '\'' +
                ", email = '" + email + '\'' +
                ", password = '" + password + '\'' +
                ", role = '" + role + '\'' +
                '}';
    }

}
