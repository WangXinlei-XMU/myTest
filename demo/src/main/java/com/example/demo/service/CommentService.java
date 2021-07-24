package com.example.demo.service;

import com.example.demo.dao.CommentDao;
import com.example.demo.model.ArticleSimple;
import com.example.demo.model.Comment;
import com.example.demo.model.Vo.CommentVo;
import com.example.demo.model.Vo.Search;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    public PageInfo<Comment> getCommentByArticle(Search search,Integer articleId){
        PageHelper.startPage(search.getCurrent(),search.getSize(),true);

        List<Comment> list= commentDao.getCommentByArticle(articleId);

        PageInfo<Comment> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public Integer addComment(CommentVo vo){
        vo.setTime(LocalDateTime.now());
        return commentDao.addComment(vo);
    }
}
