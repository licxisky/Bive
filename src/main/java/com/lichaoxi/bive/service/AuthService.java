package com.lichaoxi.bive.service;

import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.exception.UserIsExistException;
import com.lichaoxi.bive.exception.UserNotExistException;
import com.lichaoxi.bive.exception.UsernameOrPasswordErrorException;
import com.lichaoxi.bive.repository.UserRepository;
import com.lichaoxi.bive.security.CustomUserDetails;
import com.lichaoxi.bive.security.CustomUserDetailsService;
import com.lichaoxi.bive.utils.JwtUtils;
import com.lichaoxi.bive.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public User register(User user) throws UserIsExistException {
        String name = user.getName();
        if(userService.existsByName(name)) {
            throw new UserIsExistException("user has exist");
        }

        UserUtils.bcrypt(user);

        userService.create(user);

        return user;
    }

    public String login(User user) throws UserNotExistException, UsernameOrPasswordErrorException {

        String username = user.getName();

        if(!userService.existsByName(username)) {
            throw new UserNotExistException("用户不存在");
        }

        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);

        if(!UserUtils.matches(user.getPassword(), customUserDetails.getPassword())) {
            throw new UsernameOrPasswordErrorException("用户名或密码错误！");
        }

        String token = JwtUtils.generateToken(customUserDetails);

        return "Bearer " + token;
    }
}
