/**
 * Registration controller is a controller class that handles requests
 * related to the login page.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.InvalidUsernameOrPasswordException;
import com.example.WorkPortal.service.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Login page.
     */
    private static final Logger loginControllerLogger = LogManager.getLogger(IndexController.class);

    /**
     * Service handling login.
     */
    @Autowired
    private final LoginService loginService;

    /**
     * Contructs a LoginController with the specified LoginService.
     *
     * @param loginService the service handling login.
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

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

    /**
     * Handles POST request of the Login page.
     *
     * @return name of the Home page for successful login,
     * redirection to the Login page showing invalid login message.
     */
    @PostMapping("/login")
    public String loginPerson(@RequestParam String username, @RequestParam String password, Model model) {
        try {
            if (this.loginService.loginByCredentials(username, password)) {
                loginControllerLogger.info("LoginControllerLogger: Successful Login. Username and password matches that of an existing Person entity registered earlier.");
                loginControllerLogger.info("User is redirected to home.html.");
                return "redirect:/home";
            }
            throw new InvalidUsernameOrPasswordException();

        } catch (InvalidUsernameOrPasswordException e) {
            loginControllerLogger.info("LoginControllerLogger: Unsuccessful Login. InvalidUsernameOrPasswordException thrown.");
            model.addAttribute("error", "Invalid userid or password.");
            return "login";
        }

    }

}
