package com.lichaoxi.bive.controller.admin;

import com.lichaoxi.bive.controller.BaseController;
import com.lichaoxi.bive.entity.User;
import com.lichaoxi.bive.service.UserService;
import com.lichaoxi.bive.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/user")
@PreAuthorize("hasRole('ADMIN')")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping({"/{id}"})
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("")
    public User createUser(User user) {
        return userService.create(user);
    }

    @PutMapping("")
    public User editUser(User user) {
        return userService.update(UserUtils.bcrypt(user));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

}
