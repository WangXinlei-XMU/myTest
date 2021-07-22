package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleRestrict {
    String user;
    String title;
    String text;
    LocalDateTime beginTime;
    LocalDateTime endTime;
    Byte state;
}
