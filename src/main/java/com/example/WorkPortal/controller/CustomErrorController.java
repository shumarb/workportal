/**
 * Controller class handling requests related to non-existent pages.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.model.Person;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger customErrorControllerLogger = LogManager.getLogger(CustomErrorController.class);

    /**
     * Handles error requests and directs users to an error page.
     *
     * @param httpSession The HTTPSession object to manage session information.
     * @param model The Model object to pass attributes to the view.
     * @return The name of the unavailable page view.
     */
    @RequestMapping("/error")
    public String handleError(HttpSession httpSession, Model model) {
        Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
        customErrorControllerLogger.error("{} is trying to access a non-existent page or Home page without logging in.",
                                            loggedInPerson == null ? "Unknown user" : loggedInPerson.toString());
        httpSession.invalidate();
        model.addAttribute("error", "The page you are looking for is unavailable.");
        return "unavailable";
    }

}
