/**
 * Controller class that handles requests related to the home page.
 */

package com.example.WorkPortal.controller;

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
     * @return Name of the Home page.
     */
    @GetMapping("/home")
    public String showHome(HttpSession httpSession, Model model) {
        try {
            Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
            model.addAttribute("loggedInPerson", loggedInPerson);
            homeControllerLogger.info("HomeControllerLogger: Login of {} from Login page. Going to Home page.", loggedInPerson.toString());
            return "home";
        } catch (Exception e) {
            httpSession.invalidate();
            homeControllerLogger.fatal("HomeControllerLogger: Unsuccessful login despite entering correct username and password at Login page.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/index";
        }
    }

    /**
     * Handles the GET request of logout button of Home page.
     *
     * @return Name of the Index page.
     */
    @PostMapping("/")
    public String logoutOfHome(HttpSession httpSession, Model model) {
        try {
            Person loggedOutPerson = (Person) httpSession.getAttribute("loggedInPerson");
            homeControllerLogger.info("HomeControllerLogger: Logout of {} from Home page. Going to Index page. Successful logout message displayed.", loggedOutPerson.toString());
            httpSession.invalidate();
            model.addAttribute("logout", "You have been successfully logged out.");
            return "index";
        } catch (Exception e) {
            httpSession.invalidate();
            homeControllerLogger.fatal("HomeControllerLogger: Unsuccessful logout despite clicking logout button. Redirected to Index page.");
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/index";
        }
    }

}
