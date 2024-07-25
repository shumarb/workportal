/**
 * Controller class for handling language translation operations.
 */

package com.example.WorkPortal.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import java.util.Locale;

@Controller
public class LanguageController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for change in language on a page.
     */
    private static final Logger languageControllerLogger = LogManager.getLogger(LanguageController.class);

    /**
     * Determines the Locale based on the specified language parameter.
     *
     * @param language The language perimeter ('en' for English, 'fr' for French).
     * @return The Locale object corresponding to the language parameter.
     */
    protected Locale switchLocale(String language) {
        if (language.equals("fr")) {
            languageControllerLogger.info("LanguageControllerLogger: French language selected.");
            return Locale.FRENCH;
        }
        languageControllerLogger.info("LanguageControllerLogger: English (UK) language selected.");
        return Locale.UK;
    }

    /**
     * Sets the locale for provided HTTP Servlet Request and Response based on provided locale.
     *
     * @param httpServletRequest    The HTTP Servlet Request object.
     * @param httpServletResponse   The HTTP Servlet Request object.
     * @param locale The locale to be set.
     * @throws IllegalStateException If no LocaleResolver is found.
     */
    protected void setLocale(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Locale locale) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(httpServletRequest);
        if (localeResolver == null) {
            languageControllerLogger.error("LanguageControllerLogger: No LocalResolver found. IllegalStateException thrown.");
            throw new IllegalStateException("No LocaleResolver found. Check your configuration.");
        }
        localeResolver.setLocale(httpServletRequest, httpServletResponse, locale);
    }

}