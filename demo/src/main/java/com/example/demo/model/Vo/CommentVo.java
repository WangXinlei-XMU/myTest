package com.example.demo.model.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentVo {
    private Integer id;
    private Integer userId;
    private Integer articleId;
    private String desc;
    private LocalDateTime time;
}
