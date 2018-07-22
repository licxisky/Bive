package com.lichaoxi.bive.bive.controller.admin;

import com.lichaoxi.bive.bive.controller.BaseController;
import com.lichaoxi.bive.bive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "Hello, World!";
    }

}
