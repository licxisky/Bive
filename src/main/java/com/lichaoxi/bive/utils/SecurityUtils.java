package com.lichaoxi.bive.utils;

import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.repository.UserRepository;
import com.lichaoxi.bive.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    public CustomUserDetails getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByName(username);
        return new CustomUserDetails(user);
    }

}
