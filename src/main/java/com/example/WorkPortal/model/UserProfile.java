package com.example.WorkPortal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Class for a person that uses the application.
 * UserProfile is used to avoid confusion between User and Manager roles.
 */
@Entity
public class UserProfile {

    /**
     * Identification number of a UserProfile.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Name of a UserProfile.
     */
    private String name;

    /**
     * Name of a UserProfile.
     */
    private String username;

    /**
     * Email address of a UserProfile.
     */
    private String email;

    /**
     * Password of a UserProfile.
     */
    private String password;

    /**
     * Constructs a new UserProfile with the given name, username, email, and password.
     *
     * @param name     The full name of the user.
     * @param username The unique username used for login.
     * @param email    The email address associated with the user.
     * @param password The password for the user account.
     */
    public UserProfile(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
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
     * Sets the unique identifier of the UserProfile object.
     *
     * @param id The ID to set.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Retrieves the full name of the UserProfile object.
     *
     * @return The full name of the UserProfile object.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the UserProfile object.
     *
     * @param name The full name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the unique username of the UserProfile object used for login.
     *
     * @return The username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the unique username used by UserProfile object for login.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email address associated with the UserProfile object.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address associated with the UserProfile object.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password for the UserProfile object.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the UserProfile object.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
