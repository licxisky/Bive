package com.lichaoxi.bive.security;

import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByName(s);
        if(user == null) {
            throw new UsernameNotFoundException(String.format("用户名为 " + s + " 的用户不存在"));
        } else {
            return new CustomUserDetails(user);
        }
    }
}
