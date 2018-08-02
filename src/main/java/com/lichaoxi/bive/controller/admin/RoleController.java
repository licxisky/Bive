package com.lichaoxi.bive.controller.admin;

import com.lichaoxi.bive.controller.BaseController;
import com.lichaoxi.bive.entity.Role;
import com.lichaoxi.bive.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/{id}")
    public Role getRole(@PathVariable Long id) {
        return roleService.findById(id);
    }

    @PostMapping("")
    public Role createRole(Role role) {
        return roleService.create(role);
    }

    @PutMapping("")
    public Role editRole(Role role) {
        return roleService.update(role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.delete(id);
    }
}
