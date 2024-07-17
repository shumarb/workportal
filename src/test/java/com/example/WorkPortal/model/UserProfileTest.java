package com.example.WorkPortal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    private UserProfile userProfile1;

    @BeforeEach
    void setUp() {
        this.userProfile1 = new UserProfile("Sam Ray", "sam_ray33", "sam.ray@gmail.com", "sAM@r2", "User");
    }

    @Test
    void test_userProfileInstantiation() {
        assertEquals("Sam Ray", userProfile1.getName());
        assertEquals("sam_ray33", userProfile1.getUsername());
        assertEquals("sam.ray@gmail.com", userProfile1.getEmail());
        assertEquals("sAM@r2", userProfile1.getPassword());
        assertEquals("User", userProfile1.getRole());
    }

    @Test
    void test_settersUpdateAttributesCorrectly() {
        userProfile1.setName("Luke Lee");
        assertEquals(userProfile1.getName(), "Luke Lee");

        userProfile1.setUsername("luke_lee");
        assertEquals(userProfile1.getUsername(), "luke_lee");

        userProfile1.setEmail("luke.lee@outlook.com");
        assertEquals(userProfile1.getEmail(), "luke.lee@outlook.com");

        userProfile1.setPassword("MMAaf_414");
        assertEquals(userProfile1.getPassword(), "MMAaf_414");

        userProfile1.setRole("Manager");
        assertEquals(userProfile1.getRole(), "Manager");
    }

}
