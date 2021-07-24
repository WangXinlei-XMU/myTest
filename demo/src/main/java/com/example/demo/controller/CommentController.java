package com.example.demo.controller;

import com.example.demo.model.Comment;
import com.example.demo.model.Vo.CommentVo;
import com.example.demo.model.Vo.MyResult;
import com.example.demo.model.Vo.Search;
import com.example.demo.service.CommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ResponseBody
    @PostMapping("/article/{articleId}")
    public MyResult getCommentByArticle(
            @PathVariable("articleId") Integer articleId,
            @RequestBody(required = false) Search search){
        Search search1=new Search();
        if(search!=null)
            search1=search;
        PageInfo<Comment> commentPageInfo= commentService.getCommentByArticle(search1,articleId);
        MyResult myResult = new MyResult();
        myResult.setCode((int) commentPageInfo.getTotal());
        myResult.setList(commentPageInfo.getList());
        return myResult;
    }

    @ResponseBody
    @PostMapping("/add")
    public MyResult addComment(@RequestBody(required = false)
                                           CommentVo commentVo){
        CommentVo vo=new CommentVo();
        if(commentVo!=null)
            vo=commentVo;
        Integer id= commentService.addComment(vo);
        MyResult myResult = new MyResult();
        myResult.setCode(id);
        return myResult;
    }

}
