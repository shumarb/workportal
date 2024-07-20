/**
 * Unit tests for IndexPageController class method.
 */
package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    private String pageName;

    @BeforeEach
    public void setUp() {
        IndexController indexController = new IndexController();
        this.pageName = indexController.showIndexPage();
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
