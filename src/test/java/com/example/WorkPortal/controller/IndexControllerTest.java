/**
 * Unit tests for IndexController class.
 */

package com.example.WorkPortal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger indexControllerLogger = LogManager.getLogger(IndexController.class);

    @Test
    void test_showIndexPage() {
        MockitoAnnotations.openMocks(this);

        String viewName = indexController.showIndexPage();

        assertEquals("index", viewName);
    }

    @Test
    void test_doesNotShowLoginPage() {
        MockitoAnnotations.openMocks(this);

        String viewName = indexController.showIndexPage();

        assertNotEquals("login", viewName);
    }

    @Test
    void test_doesNotShowRegistrationPage() {
        MockitoAnnotations.openMocks(this);

        String viewName = indexController.showIndexPage();

        assertNotEquals("registration", viewName);
    }

}
