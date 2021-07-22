package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    Integer id;
    Integer userId;
    String title;
    String text;
    String summary;
    LocalDateTime createTime;
    LocalDateTime modifyTime;
    Byte state;
}
