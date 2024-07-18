package com.example.WorkPortal.controller;

import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * PersonController is a controller class that handles requests related to Person objects.
 */
@Controller
public class PersonController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger PersonControllerLogger = LogManager.getLogger(PersonController.class);

    @Autowired
    private final PersonService personService;

    /**
     * Constructs a new PersonController with specified PersonService.
     *
     * @param personService The service that managers Person objects.
     */
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Redirects user to the login page after successful registration.
     *
     * @param person The Person object created from information provided by user.
     * @return Redirects to the login page.
     */
    @PostMapping("/register")
    public String registerPerson(Person person) {
        this.personService.registerPerson(person);
        PersonControllerLogger.info("PersonControllerLogger: Successful Registration --> {}", person.toString());
        PersonControllerLogger.info("PersonControllerLogger: User is redirected to login.html.");
        return "redirect:/login";
    }

}
