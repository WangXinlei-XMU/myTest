package com.example.demo.model.Vo;

import lombok.Data;

import java.util.List;

@Data
public class Search {
    private String search;
    private Integer size;
    private Integer current;
    public Search(){
        search="";
        size=5;
        current=1;
    }
}
