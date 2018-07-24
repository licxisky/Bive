package com.lichaoxi.bive.controller.admin;

import com.lichaoxi.bive.controller.BaseController;
import com.lichaoxi.bive.security.CustomUserDetails;
import com.lichaoxi.bive.service.UserService;
import com.lichaoxi.bive.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user")

public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityUtils securityUtils;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping({"", "/", "/index"})
    public String index() {
        return "Hello, World!";
    }

}
