package com.example.demo.dao;

import com.example.demo.model.Article;
import com.example.demo.model.ArticleSimple;
import com.example.demo.model.Vo.ArticleLimitVo;
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
                    @Result(column = "back_url",property = "backUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public List<ArticleSimple> getArticleByUser(@Param("userId")Integer userId);

    //按状态获取全部文章
    @Select("select a.id,a.user_id,c.name,a.title,a.create_time,a.state,a.back_url " +
            "from article a,customer c " +
            "where a.user_id=c.id state=#{state}")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "user",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "title",property = "title",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "back_url",property = "backUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = LocalDateTime.class,jdbcType = JdbcType.DATETIMEOFFSET),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public List<ArticleSimple> getArticle(@Param("state")Byte state);


    //按关键词语搜索
    @Select("select a.id,a.user_id,c.name,a.title,a.create_time,a.state,a.back_url " +
            "from article a,customer c " +
            "where a.user_id=c.id and " +
            "(c.name like '%${search}%' or a.title like '%${search}%') and a.state=1")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "user",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "back_url",property = "backUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR),
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

    @Select("select a.id,a.user_id,c.name,a.title," +
            "a.context,a.summary,a.modify_time," +
            "a.create_time,a.state,a.back_url " +
            "from article a,customer c " +
            "where a.user_id=c.id and a.id=#{id}")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "back_url",property = "backUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "name",property = "user",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "title",property = "title",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = LocalDateTime.class,jdbcType = JdbcType.DATETIMEOFFSET),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public Article getArticleById(@Param("id")Integer id);

    List<ArticleSimple> getArticleByLimit(ArticleLimitVo vo);
}
