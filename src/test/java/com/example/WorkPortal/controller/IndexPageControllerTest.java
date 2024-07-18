/**
 * Unit tests for IndexPageController class method.
 */
package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexPageControllerTest {

    private String pageName;

    @BeforeEach
    public void setUp() {
        IndexPageController indexPageController = new IndexPageController();
        this.pageName = indexPageController.goToIndexPage();
    }

    @Test
    void test_retrievesIndexPage() {
        assertEquals("index", pageName);
    }

    @Test
    void test_doesNotRetrieveLoginPage() {
        assertNotEquals("login", pageName);
    }

    @Test
    void test_doesNotRetrieveRegistrationPage() {
        assertNotEquals("registration", pageName);
    }

}
