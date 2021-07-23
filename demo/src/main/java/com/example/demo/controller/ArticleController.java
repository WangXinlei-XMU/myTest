package com.example.demo.controller;

import com.example.demo.MyResult;
import com.example.demo.model.ArticleSimple;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @ResponseBody
    @GetMapping("/search")
    public MyResult register(){
        List<ArticleSimple> articleSimpleList= articleService.getArticleByUser(1);
        System.out.println(articleSimpleList);
        MyResult myResult= new MyResult();
        myResult.setList(articleSimpleList);
        return myResult;
    }
}
