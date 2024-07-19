/**
 * Registration controller is a controller class that handles requests
 * related to the login page.
 */

package com.example.WorkPortal.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    /**
     * Logger to monitor operational flow and assist in troubleshooting for Login page.
     */
    private static final Logger loginControllerLogger = LogManager.getLogger(IndexController.class);

    /**
     * Navigates to the Login page.
     *
     * @return name of the Login page.
     */
    @GetMapping("/login")
    public String showLoginPage() {
        loginControllerLogger.info("LoginControllerLogger: User is now at login.html.");
        return "login";
    }

}
