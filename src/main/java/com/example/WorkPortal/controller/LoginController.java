/**
 * Controller class that handles requests related to the Registration page.
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
    private static final Logger loginControllerLogger = LogManager.getLogger(LoginController.class);

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
     * @return Name of the Login page.
     */
    @GetMapping("/login")
    public String showLoginPage() {
        loginControllerLogger.info("LoginControllerLogger: Currently at Login page.");
        return "login";
    }

    /**
     * Handles POST request of the Login page.
     *
     * @param username the username entered by the user.
     * @param password the password entered by the user.
     * @param session the HttpSession to store logged-in person details.
     * @param model the Model to add error messages.
     * @return Name of the Home page for successful login,
     * or redirection to the Login page showing invalid login message.
     */
    @PostMapping("/login")
    public String loginPerson(@RequestParam String username, @RequestParam String password, HttpSession session, Model model) {
        try {
            // Log messages and redirection to home page for successful login.
            Person loggedInPerson = this.loginService.login(username, password);
            session.setAttribute("loggedInPerson", loggedInPerson);
            loginControllerLogger.info("LoginControllerLogger: Successful Login for Person: {}. Going to Home page.", loggedInPerson.toString());
            return "redirect:/home";

        } catch (InvalidUsernameOrPasswordException e) {
            // Error and log messages for invalid username or password.
            loginControllerLogger.error("LoginControllerLogger: Unsuccessful Login. " +
                                                 "Invalid Username ({}) or Password ({}). " +
                                                 "Currently at Login page. Error message displayed.",
                                                  username, password);
            model.addAttribute("error", "Invalid userid or password.");
            return "login";

        } catch (Exception e) {
            // Error and log messages for unexpected exceptions.
            loginControllerLogger.fatal("LoginControllerLogger: Unsuccessful Login. Unexpected error occurred during login.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "login";
        }
    }

}
