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
public class LoginPageController {
    /**
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger loginPageControllerLogger = LogManager.getLogger(IndexPageController.class);

    /**
     * Direction to the Signup page.
     *
     * @return name of the signup page.
     */
    @GetMapping("/login")
    public String goToLoginPage() {
        loginPageControllerLogger.info("LoginPageControllerLogger: User is now at login.html.");
        return "login";
    }

}
