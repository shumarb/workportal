/**
 * Controller class that handles requests related to the Login page.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.InvalidUsernameOrPasswordException;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.service.LoginService;
import jakarta.servlet.http.HttpSession;
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
    private static final Logger logger = LogManager.getLogger(LoginController.class);

    /**
     * Service handling login.
     */
    @Autowired
    private final LoginService loginService;

    /**
     * Constructs a LoginController with the specified LoginService.
     *
     * @param loginService the service handling login.
     */
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Handles the GET request of the Login page.
     *
     * @return The name of the Login page view.
     */
    @GetMapping("/login")
    public String showLoginPage() {
        logger.info("Currently at Login page.");
        return "login";
    }

    /**
     * Handles POST request of the Login page.
     *
     * @param username      The username entered by the user.
     * @param password      The password entered by the user.
     * @param httpSession   The HttpSession to store logged-in person details.
     * @param model         The Model to add error messages.
     * @return              Name of the Home page view for successful login,
     *                      Redirection to the name of the Login page view with an invalid login message.
     */
    @PostMapping("/login")
    public String loginPerson(@RequestParam String username, @RequestParam String password, HttpSession httpSession, Model model) {
        try {
            // Log messages and redirection to home page for successful login.
            logger.info("Login attempt. Username: {}, Password: {}", username, password);
            Person loggedInPerson = this.loginService.login(username, password);
            httpSession.setAttribute("loggedInPerson", loggedInPerson);
            logger.info("Successful login of {}. Going to Home page.", loggedInPerson.toString());
            return "redirect:/home";

        } catch (InvalidUsernameOrPasswordException e) {
            // Error and log messages for invalid username or password.
            logger.error("Unsuccessful Login due to invalid username ({}) or password ({}). "
                                    + "Showing Login page with error message displayed.", username, password);
            model.addAttribute("error", "Invalid userid or password");
            return "login";

        } catch (Exception e) {
            // Error and log messages for unexpected exceptions.
            logger.fatal("Unsuccessful Login. Unexpected error occurred during login.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "login";
        }
    }

}
