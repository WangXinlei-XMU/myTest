package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleSimple {
    Integer id;
    String user;
    String title;
    LocalDateTime createTime;
    Byte state;
}
