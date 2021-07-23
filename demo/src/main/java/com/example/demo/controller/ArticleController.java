package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.model.Vo.MyResult;
import com.example.demo.model.Vo.Search;
import com.example.demo.model.ArticleSimple;
import com.example.demo.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @ResponseBody
    @PostMapping("/search")
    public MyResult search(@RequestBody(required = false)Search search){
        Search search1=new Search();
        if(search!=null)
            search1=search;
//        System.out.println(search1);
//        System.out.println("key"+key);
        PageInfo<ArticleSimple> articleSimpleList= articleService.getArticleByKey(search1);
//        System.out.println(search1);
        MyResult myResult= new MyResult();
        myResult.setCode((int) articleSimpleList.getTotal());
        myResult.setList(articleSimpleList.getList());
//        System.out.println(articleSimpleList);
        return myResult;
    }

    @ResponseBody
    @GetMapping("/{articleId}")
    public MyResult getArticleById(@PathVariable("articleId") Integer id){
        Article article= articleService.getArticleById(id);
        MyResult myResult=new MyResult();
        myResult.setObj(article);
        return myResult;
    }
}
