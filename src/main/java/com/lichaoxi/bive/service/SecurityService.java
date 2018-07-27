package com.lichaoxi.bive.service;

import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.repository.UserRepository;
import com.lichaoxi.bive.security.CustomUserDetails;
import com.lichaoxi.bive.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public CustomUserDetails getCurrentUser() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return customUserDetailsService.loadUserByUsername(username);
    }

}
