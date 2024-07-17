package com.example.WorkPortal.service;

import com.example.WorkPortal.model.UserProfile;
import com.example.WorkPortal.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    public void registerUserProfile(UserProfile userProfile) {
        this.userProfileRepository.save(userProfile);
    }
}
