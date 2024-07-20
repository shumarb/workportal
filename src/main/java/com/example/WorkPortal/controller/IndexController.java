/**
 * PageController is a controller class that handles requests
 * related to the Index page.
 */

package com.example.WorkPortal.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Index page.
     */
    private static final Logger indexControllerLogger = LogManager.getLogger(IndexController.class);

    /**
     * Handles the GET request of the Index page.
     *
     * @return Name of the Index page.
     */
    @GetMapping("/")
    public String showIndexPage() {
        indexControllerLogger.info("IndexControllerLogger: Currently at Index page.");
        return "index";
    }

}
