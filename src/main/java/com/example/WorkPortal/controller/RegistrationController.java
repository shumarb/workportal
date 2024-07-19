/**
 * Registration controller is a controller class that handles requests related to
 * the registration page.
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

@Controller
public class RegistrationController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Registration page.
     */
    private static final Logger registrationControllerLogger = LogManager.getLogger(IndexController.class);

    @Autowired
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    /**
     * Navigates to the Registration page.
     *
     * @return name of the Registration page.
     */
    @GetMapping("/registration")
    public String showRegistrationPage() {
        registrationControllerLogger.info("RegistrationControllerLogger: User is now at registration.html.");
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
     * @return Redirection to the login page for successful registration,
     *         direction to registration page with error message displayed otherwise.
     */
    @PostMapping("/register")
    public String registerPerson(@RequestParam("name") String name,
                                 @RequestParam("username") String username,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role,
                                 Model model) {

        try {
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

            // Both username and email address. Proceed with instantiating and saving this Person entity.
            this.registrationService.registerPerson(name, username, email, password, role);
            registrationControllerLogger.info("RegistrationControllerLogger: Successful Registration. User is redirected to login.html.");
            return "redirect:/login";

        } catch (InvalidEmailException e) {
            // Error and log messages due to invalid email address.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid email address format: {}", email);
            model.addAttribute("error", "Invalid email address.");
            return "registration";

        } catch (InvalidNameException e) {
            // Error and log messages due to invalid name.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid name: {}", name);
            model.addAttribute("error", "Please ensure your name has only 2 words, and each word has at least 3 letters.");
            return "registration";

        } catch (InvalidPasswordException e) {
            // Error and log messages for invalid password.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid password: {}", password);
            model.addAttribute("error", "Please ensure your password has at least 2 letters, 2 digits, and 1 special character.");
            return "registration";

        } catch (InvalidUsernameException e) {
            // Error and log messages for invalid username.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Invalid username: {}", username);
            model.addAttribute("error", "Please ensure your username has at least 5 characters.");
            return "registration";

        } catch (UnavailableEmailAddressException e) {
            // Error and log messages for unavailable email address.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Email address is unavailable. Email address: {}", email);
            model.addAttribute("error", "Email address entered is unavailable. Please enter another email address.");
            return "registration";

        } catch (UnavailablePasswordException e) {
            // Error and log messages for unavailable password.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Password is unavailable. Password: {}", password);
            model.addAttribute("error", "Password entered is unavailable. Please enter another password.");
            return "registration";

        } catch (UnavailableUsernameException e) {
            // Error and log messages for unavailable username.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Username is unavailable. Username: {}", username);
            model.addAttribute("error", "Username entered is unavailable. Please enter another username.");
            return "registration";

        } catch (Exception e) {
            // Error and log messages for unexpected exceptions.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Unexpected error occurred during registration.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "registration";
        }
    }

}
