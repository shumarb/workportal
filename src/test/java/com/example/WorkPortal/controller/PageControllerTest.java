/**
 * Unit tests for PageController class methods.
 */
package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageControllerTest {

    private PageController pageController;
    private String pageName;

    @BeforeEach
    public void setUp() {
        this.pageController = new PageController();
        this.pageName = "";
    }

    @Test
    void test_goToHomePage_retrievesCorrectPage() {
        this.pageName = this.pageController.goToHomePage();
        assertEquals("home", pageName);
    }

    @Test
    void test_goToHomePage_doesNotRetrieveIncorrectPage() {
        this.pageName = this.pageController.goToHomePage();
        assertNotEquals("login", pageName);
    }

    @Test
    void test_goToLoginPage_retrievesCorrectPage() {
        this.pageName = this.pageController.goToLoginPage();
        assertEquals("login", pageName);
    }

    @Test
    void test_goToLoginPage_doesNotRetrieveIncorrectPage() {
        this.pageName = this.pageController.goToLoginPage();
        assertNotEquals("registration", pageName);
    }

    @Test
    void test_goToRegistrationPage_retrievesCorrectPage() {
        this.pageName = this.pageController.goToRegistrationPage();
        assertEquals("registration", pageName);
    }

    @Test
    void test_goToRegistrationPage_doesNotRetrieveIncorrectPage() {
        this.pageName = this.pageController.goToRegistrationPage();
        assertNotEquals("login", pageName);
    }

}
