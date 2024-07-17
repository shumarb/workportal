package com.example.WorkPortal.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    private UserProfile userProfile1;
    private UserProfile userProfile2;

   @Test
    void test_userProfileInstantiation() {
        userProfile1 = new UserProfile("Sam Ray", "sam_ray33", "sam.ray@gmail.com", "sAM@r2", "User");
        assertEquals("Sam Ray", userProfile1.getName());
        assertEquals("sam_ray33", userProfile1.getUsername());
        assertEquals("sam.ray@gmail.com", userProfile1.getEmail());
        assertEquals("sAM@r2", userProfile1.getPassword());
        assertEquals("User", userProfile1.getRole());
    }

    @Test
    void test_settersUpdateAttributesCorrectly() {
        userProfile2 = new UserProfile("Ali Hassan", "ali.hassan", "sam.ray@proton.me", "Aer_H3aH323", "Manager");

        userProfile2.setName("Luke Lee");
        assertEquals(userProfile2.getName(), "Luke Lee");

        userProfile2.setUsername("luke_lee");
        assertEquals(userProfile2.getUsername(), "luke_lee");

        userProfile2.setEmail("luke.lee@outlook.com");
        assertEquals(userProfile2.getEmail(), "luke.lee@outlook.com");

        userProfile2.setPassword("MMAaf_414");
        assertEquals(userProfile2.getPassword(), "MMAaf_414");

        userProfile2.setRole("User");
        assertEquals(userProfile2.getRole(), "User");
    }

}
