/**
 * Controller class that handles requests related to the Registration page.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.InvalidEmailException;
import com.example.WorkPortal.exceptions.InvalidNameException;
import com.example.WorkPortal.exceptions.InvalidPasswordException;
import com.example.WorkPortal.exceptions.InvalidUsernameException;
import com.example.WorkPortal.exceptions.UnavailableEmailAddressException;
import com.example.WorkPortal.exceptions.UnavailableUsernameException;
import com.example.WorkPortal.model.Person;
import com.example.WorkPortal.service.RegistrationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Registration page.
     */
    private static final Logger logger = LogManager.getLogger(RegistrationController.class);

    /**
     * Service handling registration.
     */
    @Autowired
    private final RegistrationService registrationService;

    /**
     * Constructs a RegistrationController with the specified RegistrationService.
     *
     * @param registrationService the Service class handling registration.
     */
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Handles the GET request of the Registration page.
     *
     * @return Name of the Registration page.
     */
    @GetMapping("/registration")
    public String showRegistrationPage() {
        logger.info("Currently at Registration page.");
        return "registration";
    }

    /**
     * Redirects user to the login page after successful registration.
     *
     * @param name          The name of the Person.
     * @param username      The username of the Person.
     * @param email         The email address of the Person.
     * @param password      The password of the Person.
     * @param role          The role of the Person.
     * @return              Redirection to the Login page for successful registration,
     *                      or direction to Registration page with error message for unsuccessful registration.
     */
    @PostMapping("/register")
    public String registration(@RequestParam("name") String name,
                               @RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password,
                               @RequestParam("role") String role,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        logger.info("Registration attempt. "
                + "Name: {}, "
                + "Username: {}, "
                + "Email: {}, "
                + "Password: {}, "
                + "Role: {}.", name, username, email, password, role);

        try {
            Person person = registrationService.registration(name, username, email, password, role);
            logger.info("Successful Registration of {}. Going to Login page with successful registration message displayed.", person.toString());
            redirectAttributes.addFlashAttribute("successfulRegistrationMessage", "Registration successful. Please log in.");
            return "redirect:/login";

        } catch (InvalidEmailException e) {
            handlesRegistrationError(model, "Invalid email address entered. Please enter a valid email address.", email);

        } catch (InvalidNameException e) {
            handlesRegistrationError(model, "Invalid name entered. Please enter a valid name.", name);

        } catch (InvalidPasswordException e) {
            handlesRegistrationError(model, "Invalid password entered. Please enter a valid password.", password);

        } catch (InvalidUsernameException e) {
            handlesRegistrationError(model, "Invalid username entered. Please enter a valid username.", username);

        } catch (UnavailableEmailAddressException e) {
            handlesRegistrationError(model, "Email address entered is unavailable. Please enter another email address.", email);

        } catch (UnavailableUsernameException e) {
            handlesRegistrationError(model, "Username entered is unavailable. Please enter another username.", username);

        } catch (Exception e) {
            logger.fatal("Unsuccessful Registration. Unexpected error occurred during registration.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
        }

        return "registration";
    }

    /**
     * Handles errors during registration and updates model with appropriate error message.
     *
     * @param model         The model object to add attributes for rendering the view.
     * @param errorMessage  The error message to display.
     * @param errorInput    The input causing the error.
     */
    public void handlesRegistrationError(Model model, String errorMessage, String errorInput) {
        logger.error("Registration error with input: {} | {}", errorInput, errorMessage);
        model.addAttribute("error", errorMessage);
    }

}
