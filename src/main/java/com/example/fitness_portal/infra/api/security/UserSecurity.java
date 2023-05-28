package com.example.fitness_portal.infra.api.security;

import com.example.fitness_portal.infra.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userSecurity")
public class UserSecurity {
    @Autowired
    UserService userService;

    public boolean hasUserName(Authentication authentication, String userName) {
        return userName.equals(authentication.getName());
    }
}
