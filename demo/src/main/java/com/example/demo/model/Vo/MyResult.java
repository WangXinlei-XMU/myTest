package com.example.demo.model.Vo;

import lombok.Data;

import java.util.List;

@Data
public class MyResult {
    private Integer code;
    private String msg;
    private List<?> list;
    private Object obj;
}
