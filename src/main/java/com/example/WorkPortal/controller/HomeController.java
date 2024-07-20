/**
 * Controller class that handles requests related to the home page.
 */

package com.example.WorkPortal.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Home page.
     */
    private final static Logger homeControllerLogger = LogManager.getLogger(HomeController.class);

    /**
     * Handles the GET request of the Home page.
     *
     * @return Name of the Home page.
     */
    @GetMapping("/home")
    public String showHome() {
        homeControllerLogger.info("HomeControllerLogger: Currently at Home page.");
        return "home";
    }

}
