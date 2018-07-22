package com.lichaoxi.bive.bive.repository;

import com.lichaoxi.bive.bive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
    Boolean existsByName(String name);
}
