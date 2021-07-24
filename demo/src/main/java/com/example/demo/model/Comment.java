package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {
    private Integer userId;
    private String userName;
    private String userAvatar;
    private String text;
    private LocalDateTime createTime;
}
