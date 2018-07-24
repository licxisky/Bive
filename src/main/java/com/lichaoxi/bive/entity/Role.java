package com.lichaoxi.bive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@JsonIgnoreProperties(value = {"users", "permission"})
@ToString(exclude = {"users", "permission"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.REFRESH)
    private Set<User> users;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name="role_permissions",joinColumns=@JoinColumn(name="role_id"),
            inverseJoinColumns=@JoinColumn(name="permission_id"))
    private Set<Permission> permissions;
}
