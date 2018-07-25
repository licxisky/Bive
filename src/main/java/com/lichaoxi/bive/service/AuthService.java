package com.lichaoxi.bive.service;

import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.exception.UserIsExistException;
import com.lichaoxi.bive.exception.UserNotExistException;
import com.lichaoxi.bive.repository.UserRepository;
import com.lichaoxi.bive.security.CustomUserDetails;
import com.lichaoxi.bive.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User register(User user) throws UserIsExistException {
        String name = user.getName();
        if(userRepository.existsByName(name)) {
            throw new UserIsExistException("user has exist");
        }

        String password = user.getPassword();
        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);

        return user;
    }

    public String login(User user) throws UserNotExistException {
        String username = user.getName();
        String password = user.getPassword();

        if(!userRepository.existsByName(username)) {
            throw new UserNotExistException("user not found!");
        }

        user = userRepository.findByName(username);
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        String token = JwtUtils.generateToken(customUserDetails);

        return "Bearer " + token;
    }
}
