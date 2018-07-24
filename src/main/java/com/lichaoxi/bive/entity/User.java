package com.lichaoxi.bive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties({"password"})
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private List<Role> roles;
}
