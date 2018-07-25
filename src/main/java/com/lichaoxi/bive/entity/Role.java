package com.lichaoxi.bive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(value = {"users"})
@ToString(exclude = {"users"})
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "roles")
    private List<User> users;

    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Permission> permissions;

}
