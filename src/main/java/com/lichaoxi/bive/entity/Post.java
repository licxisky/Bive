package com.lichaoxi.bive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(value = {"user"})
@ToString(exclude = {"user"})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String mdContent;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String htmlContent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
}
