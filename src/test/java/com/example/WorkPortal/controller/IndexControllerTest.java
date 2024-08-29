/**
 * Unit tests for IndexController class.
 */

package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @InjectMocks
    private IndexController indexController;

    String viewName;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        viewName = null;
    }

    @Test
    void testShowIndexPage() {
        viewName = indexController.showIndexPage();
        assertEquals("index", viewName);
    }

    @Test
    void testDoesNotShowLoginPage() {
        viewName = indexController.showIndexPage();
        assertNotEquals("login", viewName);
    }

    @Test
    void testDoesNotShowRegistrationPage() {
        viewName = indexController.showIndexPage();
        assertNotEquals("registration", viewName);
    }

}
