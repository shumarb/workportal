/**
 * Registration controller is a controller class that handles requests related to
 * the registration page.
 */
package com.example.WorkPortal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;

public class RegistrationController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger registrationControllerLogger = LogManager.getLogger(PageController.class);

    /**
     * Direction to the Registration page.
     *
     * @return name of the Registration page.
     */
    @GetMapping("/registration")
    public String goToRegistrationPage() {
        registrationControllerLogger.info("PageControllerLogger: User is now at registration.html.");
        return "registration";
    }

}
