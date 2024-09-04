/**
 * Unit tests for Internationalisation class.
 */

package com.example.WorkPortal.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class InternationalisationTest {

    @Mock
    Internationalisation internationalisation;

    @BeforeEach
    void setUp() {
        // Arrange
        internationalisation = new Internationalisation();
    }

    @Test
    void testLocaleChangeInterceptor() {
        // Act
        LocaleChangeInterceptor localeChangeInterceptor = internationalisation.localeChangeInterceptor();

        // Assert
        assertEquals(LocaleChangeInterceptor.class, localeChangeInterceptor.getClass());
        assertEquals("language", localeChangeInterceptor.getParamName());
    }

}
