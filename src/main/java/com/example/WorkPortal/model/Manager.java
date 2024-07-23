package com.example.WorkPortal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "manager")
@PrimaryKeyJoinColumn(name = "manager_id")
public class Manager extends Person {

    public Manager() {

    }

    public Manager(String name, String username, String email, String password) {
        super(name, username, email, password, "Manager");
    }
}
