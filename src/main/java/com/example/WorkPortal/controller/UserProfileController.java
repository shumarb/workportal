package com.example.WorkPortal.controller;

import com.example.WorkPortal.model.UserProfile;
import com.example.WorkPortal.service.UserProfileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * UserProfileController is a controller class that handles requests related to UserProfile objects.
 */
@Controller
public class UserProfileController {

    /**
     * Logger to monitor operational flow and assist in troubleshooting.
     */
    private static final Logger userProfileControllerLogger = LogManager.getLogger(UserProfileController.class);

    @Autowired
    private final UserProfileService userProfileService;

    /**
     * Constructs a new UserProfileController with specified UserProfileService.
     *
     * @param userProfileService The service that managers UserProfile objects.
     */
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    /**
     * Redirects user to the login page after successful registration.
     *
     * @param userProfile The UserProfile object created from information provided by user.
     * @return Redirects to the login page.
     */
    @PostMapping("/register")
    public String registerUserProfile(UserProfile userProfile) {
        this.userProfileService.registerUserProfile(userProfile);
        userProfileControllerLogger.info("UserProfileControllerLogger: Successful Registration --> {}", userProfile.toString());
        userProfileControllerLogger.info("UserProfileControllerLogger: User is redirected to login.html.");
        return "redirect:/login";
    }

}
