/**
 * PageController is a controller class that handles requests related to pages.
 */

package com.example.WorkPortal.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger pageControllerLogger = LogManager.getLogger(PageController.class);

    /**
     * Direction to the home page.
     *
     * @return name of the home page.
     */
    @GetMapping("/")
    public String goToHomePage() {
        pageControllerLogger.info("PageControllerLogger: User is now at home.html.");
        return "home";
    }

    /**
     * Direction to the Registration page.
     *
     * @return name of the Registration page.
     */
    @GetMapping("/registration")
    public String goToRegistrationPage() {
        pageControllerLogger.info("PageControllerLogger: User is now at registration.html.");
        return "registration";
    }

    /**
     * Direction to the Signup page.
     *
     * @return name of the signup page.
     */
    @GetMapping("/login")
    public String goToLoginPage() {
        pageControllerLogger.info("PageControllerLogger: User is now at login.html.");
        return "login";
    }

}
