/**
 * Registration controller is a controller class that handles requests related to
 * the registration page.
 */
package com.example.WorkPortal.controller;

import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.service.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger registrationControllerLogger = LogManager.getLogger(IndexController.class);

    @Autowired
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Navigates to the Registration page.
     *
     * @return name of the Registration page.
     */
    @GetMapping("/registration")
    public String showRegistrationPage() {
        registrationControllerLogger.info("PageControllerLogger: User is now at registration.html.");
        return "registration";
    }

    /**
     * Redirects user to the login page after successful registration.
     *
     * @param person The Person object created from information provided by user.
     * @return Redirects to the login page.
     */
    @PostMapping("/register")
    public String registerPerson(Person person) {
        this.registrationService.registerPerson(person);
        registrationControllerLogger.info("PersonControllerLogger: Successful Registration --> {}", person.toString());
        registrationControllerLogger.info("PersonControllerLogger: User is redirected to login.html.");
        return "redirect:/login";
    }

}
