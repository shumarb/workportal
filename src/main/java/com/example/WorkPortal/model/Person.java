/**
 * Class for a Person entity in the application.
 */

package com.example.WorkPortal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    /**
     * Identification number of a Person entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    /**
     * Name of a Person entity..
     */
    protected String name;

    /**
     * Username of a Person entity..
     */
    @Column(unique = true)
    protected String username;

    /**
     * Email address of a Person entity..
     */
    @Column(unique = true)
    protected String email;

    /**
     * Password of a Person entity..
     */
    protected String password;

    /**
     * Role of a Person entity..
     */
    protected String role;

    /**
     * Default constructor as required by Hibernate.
     */
    public Person() {

    }

    /**
     * Constructs a new Person entity with the given name, username, email, password, and role.
     *
     * @param name     The name of the Person entity.
     * @param username The unique username of the Person entity used for login.
     * @param email    The email address of the Person entity.
     * @param password The password for the Person entity.
     * @param role     The role for the Person entity.
     */
    public Person(String name, String username, String email, String password, String role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Retrieves the identification number of the Person entity.
     *
     * @return The identification number of the Person entity.
     */
    public long getId() { return id; }

    /**
     * Retrieves the name of the Person entity.
     *
     * @return The name of the Person entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the unique username of the Person entity.
     *
     * @return The username of the person entity.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retrieves the email address of the Person entity.
     *
     * @return The email address of the Person entity.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retrieves the password of the Person entity.
     *
     * @return The password of the Person entity.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retrieves the role for the Person object.
     *
     * @return The role of the Person entity.
     */
    public String getRole() {
        return role;
    }

    /**
     * Returns a string representation of a Person entity.
     *
     * @return a string containing the id, name, username, email address, password,
     * and role of a Person entity.
     */
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
