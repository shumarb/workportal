package com.example.WorkPortal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@PrimaryKeyJoinColumn(name = "user_id")
public class User extends Person {

    public User() {

    }

    public User(String name, String username, String email, String password) {
        super(name, username, email, password, "User");
    }
}
