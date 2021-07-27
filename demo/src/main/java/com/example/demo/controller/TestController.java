package com.example.demo.controller;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.CommentDao;
import com.example.demo.model.Article;
import com.example.demo.model.Comment;
import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.json.Json;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ArticleDao articleDao;

    @Autowired
    CommentDao commentDao;

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test")
    public String test() throws JsonProcessingException {
        Customer customer=customerService.getById(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(customer);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test2")
    public String test2() throws JsonProcessingException {
        List<Article> list=articleDao.getLikeByUser(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test3")
    public String test3() throws JsonProcessingException {
        List<Article> list=articleDao.getArticleByUserId(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }

    @CrossOrigin
    @ResponseBody
    @RequestMapping("/test4")
    public String test4() throws JsonProcessingException {
        List<Comment> list=commentDao.getCommentByArticle(1);
        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";
        jsonStr =  mapper.writeValueAsString(list);
        return jsonStr;
    }
}
