package com.lichaoxi.bive.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@JsonIgnoreProperties(value = {"post"})
@ToString(exclude = {"post"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "评论内容不能为空")
    @Size(max = 255, message = "评论长度不能超过 255 个字符")
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private Post post;

    @NotBlank(message = "用户名称不能为空")
    @Size(min = 5, max = 16, message = "用户名称长度必须在 5 - 16 个字符之间")
    private String userName;

    @Email(message = "用户邮箱格式错误")
    private String userEmail;

    private String userIp;

    private String userAgent;
}
