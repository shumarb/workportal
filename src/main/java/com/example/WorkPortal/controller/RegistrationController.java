/**
 * Controller class that handles requests related to the Registration page.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.*;
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
    private static final Logger registrationControllerLogger = LogManager.getLogger(IndexController.class);

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
        registrationControllerLogger.info("RegistrationControllerLogger: Currently at Registration page.");
        return "registration";
    }

    /**
     * Redirects user to the login page after successful registration.
     *
     * @param name the name of the Person.
     * @param username the username of the Person.
     * @param email the email address of the Person.
     * @param password the password of the Person.
     * @param role the role of the Person.
     * @return Redirection to the Login page for successful registration,
     *         direction to Registration page with error message displayed otherwise.
     */
    @PostMapping("/register")
    public String registerPerson(@RequestParam("name") String name,
                                 @RequestParam("username") String username,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        try {
            registrationControllerLogger.info("Registration attempt. "
                                                        + "Name: {}"
                                                        + ", Username: {}"
                                                        + ", Email: {}"
                                                        + ", Password: {}"
                                                        + ", Role: {}.", name, username, email, password, role);
            // Invalid email address.
            if (!this.registrationService.isValidEmailAddress(email)) {
                throw new InvalidEmailException();
            }

            // Invalid name.
            if (!this.registrationService.isValidName(name)) {
                throw new InvalidNameException();
            }

            // Invalid username.
            if (!this.registrationService.isValidPassword(password)) {
                throw new InvalidPasswordException();
            }

            // Invalid password.
            if (!this.registrationService.isValidUsername(username)) {
                throw new InvalidUsernameException();
            }

            // Unavailable email address.
            if (this.registrationService.isEmailAddressRegistered(email)) {
                throw new UnavailableEmailAddressException();
            }

            // Unavailable password.
            if (this.registrationService.isPasswordRegistered(password)) {
                throw new UnavailablePasswordException();
            }

            // Unavailable username.
            if (this.registrationService.isUsernameRegistered(username)) {
                throw new UnavailableUsernameException();
            }

            // Log message and redirection to Login page for successful registration.
            this.registrationService.registerPerson(name, username, email, password, role);
            registrationControllerLogger.info("RegistrationControllerLogger: Successful Registration. " +
                                              "User is redirected to Login page. Successful registration message displayed.");
            redirectAttributes.addFlashAttribute("successfulRegistrationMessage", "Registration successful. Please log in.");
            return "redirect:/login";

        } catch (InvalidEmailException e) {
            // Error and log messages for invalid email address.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid Email Address: {}. " +
                                                        "Currently at Registration page. Error message displayed.", email);
            model.addAttribute("error", "Invalid email address entered. Please enter a valid email address.");
            return "registration";

        } catch (InvalidNameException e) {
            // Error and log messages for invalid name.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid Name: {}. " +
                                                        "Currently at Registration page. Error message displayed.", name);
            model.addAttribute("error", "Invalid name entered. Please enter a valid name.");
            return "registration";

        } catch (InvalidPasswordException e) {
            // Error and log messages for invalid password.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid Password: {}. " +
                                                        "Currently at Registration page. Error message displayed.", password);
            model.addAttribute("error", "Invalid password entered. Please enter a valid password.");
            return "registration";

        } catch (InvalidUsernameException e) {
            // Error and log messages for invalid username.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid username: {}. " +
                                                        "Currently at Registration page. Error message displayed.", username);
            model.addAttribute("error", "Invalid username entered. Please enter a valid username.");
            return "registration";

        } catch (UnavailableEmailAddressException e) {
            // Error and log messages for unavailable email address.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Unavailable Email Address: {}. " +
                                                        "Currently at Registration page. Error message displayed.", email);
            model.addAttribute("error", "Email address entered is unavailable. Please enter another email address.");
            return "registration";

        } catch (UnavailablePasswordException e) {
            // Error and log messages for unavailable password.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Unavailable Password: {}. " +
                                                        "Currently at Registration page. Error message displayed.", password);
            model.addAttribute("error", "Password entered is unavailable. Please enter another password.");
            return "registration";

        } catch (UnavailableUsernameException e) {
            // Error and log messages for unavailable username.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Unavailable Username: {}." +
                                                        "Currently at Registration page. Error message displayed.", username);
            model.addAttribute("error", "Username entered is unavailable. Please enter another username.");
            return "registration";

        } catch (Exception e) {
            // Error and log messages for unexpected exceptions.
            registrationControllerLogger.fatal("RegistrationControllerLogger: Unsuccessful Registration. " +
                                               "Unexpected error occurred during registration.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "registration";
        }
    }

}
