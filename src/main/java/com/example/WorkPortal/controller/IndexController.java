/**
 * Controller class that handles requests related to the Index page.
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
    private static final Logger logger = LogManager.getLogger(IndexController.class);

    /**
     * Handles the GET request of the Index page.
     *
     * @return The view name for the Index page.
     */
    @GetMapping("/")
    public String showIndexPage() {
        logger.info("Currently at Index page.");
        return "index";
    }

}
