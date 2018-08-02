package com.lichaoxi.bive.service;

import com.lichaoxi.bive.entity.Role;
import com.lichaoxi.bive.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role findById(Long id) {
        return roleRepository.getOne(id);
    }

    public Role create(Role role) {
        return roleRepository.save(role);
    }

    public Role update(Role role) {
        return roleRepository.save(role);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }

    public void delete(Long id) {
        roleRepository.delete(findById(id));
    }
}
