package com.example.demo.model;

import lombok.Data;

import java.util.List;

@Data
public class Customer {
    Integer id;
    String name;
    String password;
    String introduce;
    String email;
    String phone;
    String backUrl;
    String avatarUrl;
    Byte type;
    Byte state;
}
