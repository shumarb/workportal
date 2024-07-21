/**
 * Controller class handling requests related to non-existent pages.
 */

package com.example.WorkPortal.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    /**
     * Handles error requests and directs users to an error page.
     *
     * @param httpSession The HTTPSession object to manage session information.
     * @param model The Model object to pass attributes to the view.
     * @return The name of the unavailable page view.
     */
    @RequestMapping("/error")
    public String handleError(HttpSession httpSession, Model model) {
        httpSession.invalidate();
        model.addAttribute("error", "The page you are looking for is unavailable.");
        return "unavailable";
    }

}
