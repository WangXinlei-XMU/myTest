package com.example.demo.controller;

import com.example.demo.model.ArticleSimple;
import com.example.demo.model.Browse;
import com.example.demo.model.Vo.MyResult;
import com.example.demo.model.Vo.Search;
import com.example.demo.service.OtherService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


//浏览记录和收藏
@Controller
@RequestMapping("/other")
public class OtherController {
    @Autowired
    private OtherService otherService;

    //增加一条浏览记录
    @ResponseBody
    @PostMapping("/browse/article/{artId}/user/{userId}")
    public void addBrowser(@PathVariable("artId")Integer artId,
                           @PathVariable("userId")Integer userId){
//        System.out.println("aId:"+artId+"uId:"+userId);
        otherService.addBrowse(artId,userId);
    }


    //删去浏览记录
    @ResponseBody
    @DeleteMapping("/browse/delete//{userId}")
    public MyResult deleteBrowse(@PathVariable("userId")Integer userId){
//        System.out.println(userId);
        otherService.deleteBrowse(userId);
        MyResult myResult=new MyResult();
        return myResult;
    }


    //查询浏览
    @ResponseBody
    @PostMapping("/browse/user/{userId}")
    public MyResult getBrowseByUser(@RequestBody Search search,
                                    @PathVariable("userId")Integer userId){
        MyResult myResult=new MyResult();
        PageInfo<Browse> browsePageInfo=otherService.getBrowseByUser(search,userId);
        myResult.setList(browsePageInfo.getList());
        myResult.setCode((int)browsePageInfo.getTotal());
//        System.out.println(myResult.getList());
        return myResult;
    }

    //查询喜爱
    @ResponseBody
    @PostMapping("/like/user/{userId}")
    public MyResult getLikeByUser(@RequestBody Search search,
                                    @PathVariable("userId")Integer userId){
        MyResult myResult=new MyResult();
        PageInfo<ArticleSimple> browsePageInfo=otherService.getLikeByUser(search,userId);
        myResult.setList(browsePageInfo.getList());
        myResult.setCode((int)browsePageInfo.getTotal());
//        System.out.println(myResult.getList());
        return myResult;
    }

    //了解是否喜欢此文章
    @ResponseBody
    @GetMapping("/like/article/{artId}/user/{userId}")
    public MyResult getLikeArticleUser(@PathVariable("artId")Integer artId,
                                       @PathVariable("userId")Integer userId){
//        System.out.println("aId:"+artId+"uId:"+userId);
        MyResult myResult=new MyResult();
        if(otherService.getLikeArticleUser(artId,userId))
            myResult.setCode(0);
        else myResult.setCode(1);
        return myResult;
    }

    //喜欢或不喜欢此文章
    @ResponseBody
    @PostMapping("/like/article/{artId}/user/{userId}")
    public MyResult updateLikeArticleUser(@PathVariable("artId")Integer artId,
                                   @PathVariable("userId")Integer userId){
        MyResult myResult=new MyResult();
        otherService.updateLikeArticleUser(artId,userId);
        return myResult;
    }


}
