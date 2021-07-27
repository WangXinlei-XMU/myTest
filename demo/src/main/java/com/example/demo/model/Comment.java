package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    Integer id;
    Integer user_id;
    Integer article_id;
    String text;
    Date create_time;
}
