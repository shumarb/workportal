/**
 * Controller class that handles requests related to the Home page.
 */

package com.example.WorkPortal.controller;

import com.example.WorkPortal.exceptions.RestrictedAccessException;
import com.example.WorkPortal.model.Person;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
public class HomeController extends LanguageController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting for Home page.
     */
    private static final Logger logger = LogManager.getLogger(HomeController.class);

    /**
     * Handles the GET request of the Home page.
     *
     * @param httpSession       The HttpSession object to retrieve logged-in user information.
     * @param model             The Model object to add attributes for rendering the view.
     * @return                  The view name "home" for successful retrieval of logged-in Person entity's information,
     *                          or redirects to "/index" if unexpected error occurs.
     */
    @GetMapping("/home")
    public String showHome(HttpSession httpSession, Model model) {
        Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
        try {
            model.addAttribute("loggedInPerson", loggedInPerson);
            logger.info("Currently at Home page. Accessed by {}", loggedInPerson.toString());
            return "home";
        } catch (Exception e) {
            httpSession.invalidate();
            logger.fatal("Unsuccessful login of {} despite entering correct username and password at Login page.", loggedInPerson.toString());
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/index";
        }
    }

    /**
     * Handles the POST request of logout button of Home page.
     *
     * @param httpSession       The HttpSession object to retrieve logged-in user information.
     * @param model             The Model object to add attributes for rendering the view.
     * @return                  The view name "index" for successful logout,
     *                          or redirects to "/home" if unexpected error occurs.
     */
    @PostMapping("/")
    public String logoutOfHome(HttpSession httpSession, Model model) {
        Person loggedOutPerson = (Person) httpSession.getAttribute("loggedInPerson");
        try {
            logger.info("Successful logout of {} from Home page. Going to Index page with successful logout message displayed.", loggedOutPerson.toString());
            httpSession.invalidate();
            model.addAttribute("logout", "You have been successfully logged out.");
            return "index";
        } catch (Exception e) {
            httpSession.invalidate();
            logger.fatal("Unsuccessful logout of {} despite clicking logout button. Redirected to Index page with error message displayed.", loggedOutPerson.toString());
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/home";
        }
    }

    /**
     * Handles the GET request of Managerial Code of Conduct page.
     *
     * @param httpSession       The HttpSession object to retrieve information of a logged-in Person entity.
     * @param model             The model object to add attributes for view rendering.
     * @return                  The view name "managerial-code-of-conduct" if the Person entity's role is a Manager,
     *                          "access-denied" if Person entity's role is not a Manager,
     *                          Redirects to "/home" if an unexpected error occurs.
     */
    @GetMapping("/managerial-code-of-conduct")
    public String showManagerialCodeOfConduct(HttpSession httpSession, Model model) {
        Person loggedInPerson = (Person) httpSession.getAttribute("loggedInPerson");
        try {
            if (loggedInPerson != null && loggedInPerson.getRole().equals("Manager")) {
                logger.info("Currently at Managerial Code of Conduct page. Accessed by {}", loggedInPerson.toString());
                return "managerial-code-of-conduct";
            } else {
                throw new RestrictedAccessException();
            }

        } catch (RestrictedAccessException e) {
            httpSession.invalidate();
            logger.error("Restricted access attempt to Managerial Code of Conduct by {}. Redirected to Access Denied page.",
                                        loggedInPerson == null ? "Unknown user" : loggedInPerson.toString());
            model.addAttribute("error", "You do not have permission to access this page.");
            return "access-denied";

        } catch (Exception e) {
            httpSession.invalidate();
            if (loggedInPerson != null) {
                logger.error("Unable to access Managerial Code of Conduct page for {}. Redirected to Home page.", loggedInPerson.toString());
            }
            model.addAttribute("error", "Unexpected error occurred. Please try again later.");
            return "redirect:/home";
        }
    }

    /**
     * Handles the GET request for changing of language on the Managerial Code of Conduct page.
     *
     * @param language                  The language parameter indicating the selected language ('en' for English, 'fr' for French).
     * @param httpServletRequest        The HTTP request object.
     * @param httpServletResponse       The HTTP response object.
     * @return                          The view name for redirecting to the Managerial Code of Conduct page with content in the selected language.
     * @throws IllegalStateException    If no LocaleResolver is found in the application context.
     */
    @GetMapping("/locale")
    public String changeLocale(@RequestParam String language,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
        Locale locale = switchLocale(language);
        setLocale(httpServletRequest, httpServletResponse, locale);
        return "redirect:/managerial-code-of-conduct";
    }

}
