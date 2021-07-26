package com.example.demo.controller;

import com.example.demo.model.Article;
import com.example.demo.model.Label;
import com.example.demo.model.Vo.ArticleLimitVo;
import com.example.demo.model.Vo.MyResult;
import com.example.demo.model.Vo.Search;
import com.example.demo.model.ArticleSimple;
import com.example.demo.service.ArticleService;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
    public MyResult getArticleById(
            @PathVariable("articleId") Integer id){
        Article article= articleService.getArticleById(id);
        MyResult myResult=new MyResult();
        myResult.setObj(article);
        return myResult;
    }


    @ResponseBody
    @PostMapping("/user/{userId}")
    public MyResult getArticleByUserId(
            @PathVariable("userId") Integer userId,
            @RequestBody(required = false) Search search){
        Search search1=new Search();
        if(search!=null)
            search1=search;

//        System.out.println(search1);
//        System.out.println(userId);

        PageInfo<ArticleSimple> articleSimpleList= articleService.getArticleByUser(search1,userId);
//        System.out.println(articleSimpleList);
        MyResult myResult= new MyResult();
        myResult.setCode((int) articleSimpleList.getTotal());
        myResult.setList(articleSimpleList.getList());
        return myResult;
    }

    @ResponseBody
    @PostMapping("/limit")
    public MyResult getArticleService(@RequestBody ArticleLimitVo articleLimitVo) {
        PageInfo<ArticleSimple> articleSimpleList= articleService.getArticleByLimit(articleLimitVo);
        MyResult myResult=new MyResult();

        myResult.setCode((int) articleSimpleList.getTotal());
        myResult.setList(articleSimpleList.getList());
        return myResult;
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public MyResult deleteArticle(@PathVariable("id") Integer id){
        MyResult myResult=new MyResult();
//        System.out.println(id);
        articleService.deleteArticle(id);
        myResult.setCode(0);
        return myResult;
    }

    @ResponseBody
    @GetMapping("/labels")
    public MyResult getLabel(){
        MyResult myResult=new MyResult();
        List<Label> list=articleService.getLabels();
        myResult.setList(list);
        return myResult;
    }

    @ResponseBody
    @PostMapping("/newArticle")
    public MyResult insertArticle(@RequestBody Article article){
        MyResult myResult=new MyResult();
        articleService.insertArticle(article);
        myResult.setCode(0);
        return myResult;
    }

    @ResponseBody
    @PutMapping("/update")
    public MyResult update(@RequestBody Article article){
        MyResult myResult=new MyResult();
//        System.out.println(article);
        articleService.update(article);
        myResult.setCode(0);
        return myResult;
    }

    //管理员相关
    @ResponseBody
    @PostMapping("/admin/article")
    public MyResult adminGetArticle(@RequestBody(required = false) Search search){
        MyResult myResult=new MyResult();
        PageInfo<ArticleSimple> articleSimpleList=articleService.getArticle(search);
//        System.out.println(articleSimpleList);
        myResult.setCode((int) articleSimpleList.getTotal());
        myResult.setList(articleSimpleList.getList());
        return myResult;
    }


    @ResponseBody
    @PostMapping("/admin/article/limit/")
    public MyResult adminGetArticleKey(@RequestBody(required = false)ArticleLimitVo vo){
        PageInfo<ArticleSimple> articleSimpleList= articleService.getArticleByLimit(vo);
//        System.out.println(search1);
//        System.out.println(vo);
        MyResult myResult= new MyResult();
        myResult.setCode((int) articleSimpleList.getTotal());
        System.out.println(articleSimpleList);
        myResult.setList(articleSimpleList.getList());
//        System.out.println(articleSimpleList);
        return myResult;
    }

    @ResponseBody
    @PutMapping("/admin/banArticle/{artId}")
    public MyResult adminBanArticle(@PathVariable("artId")Integer id) {
        MyResult myResult=new MyResult();
        articleService.banArticleState(id);
        myResult.setCode(0);
        return myResult;
    }

    @ResponseBody
    @PutMapping("/admin/unBanArticle/{artId}")
    public MyResult adminUnBanArticle(@PathVariable("artId")Integer id) {
        MyResult myResult=new MyResult();
        articleService.banArticleState(id);
        myResult.setCode(0);
        return myResult;
    }
}
