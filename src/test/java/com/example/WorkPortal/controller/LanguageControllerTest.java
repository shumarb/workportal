/**
 * Unit tests for LanguageController class.
 */

package com.example.WorkPortal.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LanguageControllerTest {

    @InjectMocks
    private LanguageController languageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSwitchLocaleForUkEnglish() {
        // Act
        Locale result = languageController.switchLocale("en");

        // Assert
        assertEquals(Locale.UK, result);
    }

}
