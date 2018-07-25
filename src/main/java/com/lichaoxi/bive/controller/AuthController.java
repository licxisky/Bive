package com.lichaoxi.bive.controller;

import com.lichaoxi.bive.entity.Role;
import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.exception.UserIsExistException;
import com.lichaoxi.bive.exception.UserNotExistException;
import com.lichaoxi.bive.jwt.JWTAuthenticationResponse;
import com.lichaoxi.bive.repository.RoleRepository;
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

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping({"/init"})
    public User init() {
        User user = new User();
        user.setName("lichaoxi");
        user.setEmail("i@lichaoxi.com");
        user.setPassword(bCryptPasswordEncoder.encode("123456"));

        Role role = new Role();
        role.setName("ROLE_ADMIN");
        Role role1 = new Role();
        role1.setName("ROLE_USER");

        user.getRoles().add(role);

        roleRepository.save(role);
        roleRepository.save(role1);

        userRepository.save(user);
        return user;
    }


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
