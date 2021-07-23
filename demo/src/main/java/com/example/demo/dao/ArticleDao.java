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

    //按状态获取全部文章
    @Select("select a.id,c.name,a.title,a.create_time,a.state\n" +
            "        from article a,customer c\n" +
            "        where a.user_id=c.id state=#{state}")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "title",property = "title",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = LocalDateTime.class,jdbcType = JdbcType.DATETIMEOFFSET),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public List<ArticleSimple> getArticle(@Param("state")Byte state);


    //按关键词语搜索
    @Select("select a.id,c.name,a.title,a.create_time,a.state " +
            "from article a,customer c " +
            "where a.user_id=c.id and " +
            "(c.name like '%${search}%' or a.title like '%${search}%')")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "title",property = "title",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = LocalDateTime.class,jdbcType = JdbcType.DATETIMEOFFSET),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public List<ArticleSimple> getArticleByKey(@Param("search")String search);



    @Select("select L.text from article_label A,label L " +
            "where A.article_id=#{articleId} and A.label_id=L.id")
    //获得文章标签
    public List<String> getLabel(@Param("articleId") Integer articleId);
}
