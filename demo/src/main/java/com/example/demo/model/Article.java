package com.example.demo.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Article {
    Integer id;
    String user;
    Integer userId;
    String title;
    String context;
    String summary;
    String backUrl;
    List<String> labels;
    LocalDateTime createTime;
    LocalDateTime modifyTime;
    Byte state;
}
