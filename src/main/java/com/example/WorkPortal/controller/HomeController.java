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
        Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
        if (loggedInPerson == null) {
            return "redirect:/login";
        }
        model.addAttribute("loggedInPerson", loggedInPerson);
        homeControllerLogger.info("HomeControllerLogger: Currently at Home page.");
        return "home";
    }

    /**
     * Handles the GET request of logout button of Home page.
     *
     * @return Name of the Index page.
     */
    @PostMapping("/")
    public String logoutOfHome(HttpSession session, Model model) {
        homeControllerLogger.info("HomeControllerLogger: Logging out from Home page. Going to Index page. Successful logout message displayed.");
        session.invalidate();
        model.addAttribute("logout", "You have been successfully logged out.");
        return "index";
    }

}
