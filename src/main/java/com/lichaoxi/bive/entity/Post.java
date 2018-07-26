package com.lichaoxi.bive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
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

    @NotBlank(message = "博文名称不能为空")
    private String title;

    @Size(max = 500000, message = "文章内容长度不能超过 500000 个字节")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String mdContent;

    @Size(max = 500000, message = "文章内容长度不能超过 500000 个字节")
    @Column(columnDefinition = "MEDIUMTEXT")
    private String htmlContent;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();
}
