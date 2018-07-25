package com.lichaoxi.bive.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
<<<<<<< HEAD
=======
import lombok.EqualsAndHashCode;
>>>>>>> 15d0b171a65b0a83686846295cd3ce4ac87c6b40
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(value = {"password"})
@ToString(exclude = {"password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Post> posts;

}
