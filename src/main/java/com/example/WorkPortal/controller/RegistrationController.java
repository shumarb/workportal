/**
 * Registration controller is a controller class that handles requests related to
 * the registration page.
 */
package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.UnavailableEmailAddressAndUsernameException;
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
     * @return Redirects to the login page.
     */
    @PostMapping("/register")
    public String registerPerson(@RequestParam("name") String name,
                                 @RequestParam("username") String username,
                                 @RequestParam("email") String email,
                                 @RequestParam("password") String password,
                                 @RequestParam("role") String role,
                                 Model model) {

        try {
            // 1. Unavailable email address & available username
            if (this.registrationService.isEmailAddressRegistered(email) && this.registrationService.isUsernameRegistered(username)) {
                throw new UnavailableEmailAddressAndUsernameException();
            }

            // 2. Unavailable email address
            if (this.registrationService.isEmailAddressRegistered(email)) {
                throw new UnavailableEmailAddressException();
            }

            // 3. Unavailable username
            if (this.registrationService.isUsernameRegistered(username)) {
                throw new UnavailableUsernameException();
            }

            // 4. Both username and email address. Proceed with instantiating and saving this Person entity.
            this.registrationService.registerPerson(name, username, email, password, role);
            registrationControllerLogger.info("RegistrationControllerLogger: Successful Registration. User is redirected to login.html.");
            return "redirect:/login";

        } catch (UnavailableEmailAddressAndUsernameException e) {
            // Error and log messages for both email address and username being unavailable.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Both email address and username are unavailable. Email address: {}, Username: {}", email, username);
            registrationControllerLogger.error("User is directed to registration.html");
            model.addAttribute("error", "Both username and email address entered are unavailable. Please choose others.");
            return "registration";

        } catch (UnavailableUsernameException e) {
            // Error and log messages for unavailable username.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Username is unavailable. Username: {}", username);
            registrationControllerLogger.error("User is directed to registration.html");
            model.addAttribute("error", "Username entered is unavailable. Please enter another username.");
            return "registration";

        } catch (UnavailableEmailAddressException e) {
            // Error and log messages for unavailable email address.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Email address is unavailable. Email address: {}", email);
            registrationControllerLogger.error("User is directed to registration.html");
            model.addAttribute("error", "Email address entered is unavailable. Please enter another email address.");
            return "registration";

        } catch (Exception e) {
            // Error and log messages for unexpected exceptions.
            registrationControllerLogger.error("RegistrationControllerLogger: Unsuccessful Registration. Unexpected error occurred during registration.");
            registrationControllerLogger.error("User is directed to registration.html");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "registration";
        }
    }

}
