package com.example.WorkPortal.repository;

import com.example.WorkPortal.model.UserProfile;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    UserProfile findByEmail(String email);
    UserProfile findByPassword(String password);
    UserProfile findByUsername(String username);
}
