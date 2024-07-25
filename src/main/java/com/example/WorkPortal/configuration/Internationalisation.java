/**
 * Configuration class for supporting language translation in application.
 * Implements {@link WebMvcConfigurer} to customize Spring MVC configuration.
 */

package com.example.WorkPortal.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class Internationalisation implements WebMvcConfigurer {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for change in language on a page.
     */
    private static final Logger internationalisationLogger = LogManager.getLogger(Internationalisation.class);

    /**
     * Defines the locale resolver bean for session-based locale management.
     * Sets the default locale to {@link Locale#ENGLISH}.
     *
     * @return The {@link LocaleResolver} instance configured for session-based locale management.
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        sessionLocaleResolver.setDefaultLocale(Locale.UK);
        internationalisationLogger.info("InternationalisationLogger: SessionLocaleResolver has been created with default locale of {}",Locale.UK);
        return sessionLocaleResolver;
    }

    /**
     * Defines the locale change interceptor bean to intercept language change requests.
     * Sets the parameter name for language selection to "language".
     *
     * @return The {@link LocaleChangeInterceptor} instance configured with the language parameter name.
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        internationalisationLogger.info("InternationalisationLogger: LocaleChangeInterceptor has been created with parameter name of {}", "language");
        return localeChangeInterceptor;
    }

    /**
     * Adds the locale change interceptor to the application's interceptor registry.
     *
     * @param registry The {@link InterceptorRegistry} used to register interceptors.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        internationalisationLogger.info("InternationalisationLogger: LocaleChangeInterceptor has been added to InterceptorRegistry.");
    }

    /**
     * Configures resource handlers to serve static resources from the classpath.
     *
     * @param registry The {@link ResourceHandlerRegistry} used to register resource handlers.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        internationalisationLogger.info("InternationalisationLogger: Resource handler configured for classpath: {}", "/static/**");

    }

}
