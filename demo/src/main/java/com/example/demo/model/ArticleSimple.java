package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ArticleSimple {
    Integer id;
    String user;
    String title;
    List<String> labels;
    String url;
    LocalDateTime createTime;
    Byte state;
}
