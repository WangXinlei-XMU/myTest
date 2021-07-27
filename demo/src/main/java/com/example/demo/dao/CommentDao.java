package com.example.demo.dao;

import com.example.demo.model.Article;
import com.example.demo.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentDao {

    @Select("select * from comment where article_id=#{aid}")
    public List<Comment> getCommentByArticle(@Param("aid")Integer aid);
}
