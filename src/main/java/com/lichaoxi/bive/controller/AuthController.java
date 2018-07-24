package com.lichaoxi.bive.controller;

import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.exception.UserIsExistException;
import com.lichaoxi.bive.exception.UserNotExistException;
import com.lichaoxi.bive.jwt.JWTAuthenticationResponse;
import com.lichaoxi.bive.repository.UserRepository;
import com.lichaoxi.bive.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public User register(User user) throws UserIsExistException {
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(User user) throws UserNotExistException {
        String token = authService.login(user);
        return ResponseEntity.ok(new JWTAuthenticationResponse(token));
    }


}
