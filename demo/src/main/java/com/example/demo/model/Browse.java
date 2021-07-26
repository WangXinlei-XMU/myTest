package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Browse {
    String articleTitle;
    Integer articleId;
    String userName;
    Integer userId;
    LocalDateTime time;
}
