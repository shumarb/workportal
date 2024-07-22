/**
 * Controller class that handles requests related to the Home page.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.RestrictedAccessException;
import com.example.WorkPortal.model.Person;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Home page.
     */
    private static final Logger homeControllerLogger = LogManager.getLogger(HomeController.class);

    /**
     * Handles the GET request of the Home page.
     *
     * @param httpSession       The HttpSession object to retrieve logged-in user information.
     * @param model             The Model object to add attributes for rendering the view.
     * @return The view name    "home" for successful retrieval of logged-in Person entity's information,
     *                          or redirects to "/index" if unexpected errors occur.
     */
    @GetMapping("/home")
    public String showHome(HttpSession httpSession, Model model) {
        Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
        try {
            model.addAttribute("loggedInPerson", loggedInPerson);
            homeControllerLogger.info("HomeControllerLogger: {} is currently at Home page.", loggedInPerson.toString());
            return "home";
        } catch (Exception e) {
            httpSession.invalidate();
            homeControllerLogger.fatal("HomeControllerLogger: Unsuccessful login of {} despite entering correct username and password at Login page.", loggedInPerson.toString());
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/index";
        }
    }

    /**
     * Handles the POST request of logout button of Home page.
     *
     * @param httpSession       The HttpSession object to retrieve logged-in user information.
     * @param model             The Model object to add attributes for rendering the view.
     * @return The view name    "index" for successful logout,
     *                          redirection to "/home" for unexpected errors.
     */
    @PostMapping("/")
    public String logoutOfHome(HttpSession httpSession, Model model) {
        Person loggedOutPerson = (Person) httpSession.getAttribute("loggedInPerson");
        try {
            homeControllerLogger.info("HomeControllerLogger: Logout of {} from Home page. Going to Index page. Successful logout message displayed.", loggedOutPerson.toString());
            httpSession.invalidate();
            model.addAttribute("logout", "You have been successfully logged out.");
            return "index";
        } catch (Exception e) {
            httpSession.invalidate();
            homeControllerLogger.fatal("HomeControllerLogger: Unsuccessful logout of {} despite clicking logout button. Redirected to Index page.", loggedOutPerson.toString());
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/home";
        }
    }

    /**
     * Handles the GET request of Managerial Code of Conduct page.
     *
     * @param httpSession       The HttpSession object to retrieve information of a logged-in Person entity.
     * @param model             The model object to add attributes for view rendering.
     * @return the view name    "managerial-code-of-conduct" if the Person entity's role is a Manager,
     *                          "access-denied" if Person entity's role is not a Manager,
     *                          Redirects to "/home" if an unexpected error occurs during processing.
     */
    @GetMapping("/managerial-code-of-conduct")
    public String showManagerialCodeOfConduct(HttpSession httpSession, Model model) {
        Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
        try {
            if (loggedInPerson != null && loggedInPerson.getRole().equals("Manager")) {
                homeControllerLogger.info("HomeControllerLogger: Currently at Managerial Code of Conduct page. Accessed by {}", loggedInPerson.toString());
                return "managerial-code-of-conduct";
            } else {
                throw new RestrictedAccessException();
            }

        } catch (RestrictedAccessException e) {
            httpSession.invalidate();
            homeControllerLogger.error("HomeControllerLogger: Restricted access attempt to Managerial Code of Conduct by {}. Redirected to Access Denied page.",
                                        loggedInPerson == null ? "Unknown user" : loggedInPerson.toString());
            model.addAttribute("error", "You do not have permission to access this page.");
            return "access-denied";

        } catch (Exception e) {
            httpSession.invalidate();
            if (loggedInPerson != null) {
                homeControllerLogger.error("HomeControllerLogger: Unable to access Managerial Code of Conduct page for {}. Redirected to Home page.", loggedInPerson.toString());
            }
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/home";
        }
    }

}
