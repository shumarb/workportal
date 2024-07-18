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
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger indexPageControllerLogger = LogManager.getLogger(IndexController.class);

    /**
     * Direction to the home page.
     *
     * @return name of the home page.
     */
    @GetMapping("/")
    public String goToIndexPage() {
        indexPageControllerLogger.info("IndexPageControllerLogger: User is now at index.html.");
        return "index";
    }

}
