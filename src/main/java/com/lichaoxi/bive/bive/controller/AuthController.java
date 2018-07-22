package com.lichaoxi.bive.bive.controller;

import com.lichaoxi.bive.bive.entity.User;
import com.lichaoxi.bive.bive.exception.UserExistException;
import com.lichaoxi.bive.bive.exception.UserNotFoundException;
import com.lichaoxi.bive.bive.jwt.JWTAuthenticationResponse;
import com.lichaoxi.bive.bive.service.AuthService;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public User register(User user) throws UserExistException {
        return authService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(User user) throws UserNotFoundException {
        String token = authService.login(user);
        return ResponseEntity.ok(new JWTAuthenticationResponse(token));
    }
}
