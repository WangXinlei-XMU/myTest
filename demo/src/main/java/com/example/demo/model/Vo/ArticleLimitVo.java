package com.example.demo.model.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleLimitVo {
    private String user;
    private String title;
    private LocalDateTime date1;
    private LocalDateTime date2;
    private Integer size;
    private Integer current;
    public ArticleLimitVo(){
        user=title="";
        size=5;
        current=1;
    }
}
