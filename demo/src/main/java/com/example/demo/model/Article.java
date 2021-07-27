package com.example.demo.model;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class Article {
    Integer id;
    Integer user_id;
    String title;
    String content;
    String summary;
    Date create_Time;
    Date modify_Time;
    Byte state;
}
