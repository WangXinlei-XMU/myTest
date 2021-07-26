package com.example.demo.dao;

import com.example.demo.model.ArticleSimple;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface ArticleDao {
    //获取某个人的全部文章简介

    @Select("select * from article where user_id=#{userId}")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "title",property = "title",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = LocalDateTime.class,jdbcType = JdbcType.DATETIMEOFFSET),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public List<ArticleSimple> getArticleByUser(@Param("userId")Integer userId);

    @Select("select L.content from article_label A,label L " +
            "where A.article_id=#{articleId} and A.label_id=L.id")
    //获得文章标签
    public List<String> getLabel(@Param("articleId") Integer articleId);
}
