package com.example.demo.dao;

import com.example.demo.model.ArticleSimple;
import com.example.demo.model.Browse;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OtherDao {

    //增加一条浏览记录
    @Insert("insert into browse (article_id,user_id,time,be_deleted) values " +
            "(#{artId},#{userId},#{time},0)")
    public void addBrowse(@Param("artId")Integer artId,
                          @Param("userId")Integer userId,
                          @Param("time")LocalDateTime time);
    //查询浏览
    @Select("select b.article_id,a.title,b.user_id,c.name,time " +
            "from browse b,article a,customer c " +
            "where b.user_id=#{userId} and a.id=b.article_id and " +
            "c.id=b.user_id and b.be_deleted=0")
    @Results(
            value = {
                    @Result(column = "article_id",property = "articleId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "title",property = "articleTitle",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "userName",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "time",property = "time",javaType = LocalDateTime.class,jdbcType = JdbcType.TIMESTAMP),
            }
    )
    public List<Browse> getBrowseByUser(@Param("userId")Integer userId);


    @Update("update browse set be_deleted=1 where user_id=#{userId}")
    void deleteBrowse(@Param("userId") Integer userId);

    //查询喜爱
    @Select("select a.id,c.name,a.user_id,a.title," +
            "a.back_url,a.create_time " +
            "from article a,customer c,likes l " +
            "where l.user_id=#{userId} and l.article_id=a.id " +
            "and a.user_id=c.id and a.state=1")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "user",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "title",property = "title",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "back_url",property = "backUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = LocalDateTime.class,jdbcType = JdbcType.TIMESTAMP),
            }
    )
    List<ArticleSimple> getLikeByUser(Integer userId);

    //了解是否喜欢此文章
    @Select("select id from likes where " +
            "article_id=#{artId} and user_id=#{userId}")
    public Integer getLikeArticleUser(@Param("artId")Integer artId,
                                      @Param("userId")Integer userId);

    //喜欢文章
    @Insert("insert into likes (article_id,user_id) values " +
            "(#{artId},#{userId})")
    public void addLikeArticleUser(@Param("artId")Integer artId,
                                   @Param("userId")Integer userId);

    //不喜欢文章
    @Delete("delete from likes where " +
            "article_id=#{artId} and user_id=#{userId}")
    public void deleteLikeArticleUser(@Param("artId")Integer artId,
                                      @Param("userId")Integer userId);


    //管理员相关部分
    //获取全部浏览
    @Select("select b.article_id,a.title,b.user_id,c.name,time " +
            "from browse b,article a,customer c " +
            "where a.id=b.article_id and " +
            "c.id=b.user_id")
    @Results(
            value = {
                    @Result(column = "article_id",property = "articleId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "title",property = "articleTitle",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "userName",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "time",property = "time",javaType = LocalDateTime.class,jdbcType = JdbcType.TIMESTAMP),
            }
    )
    List<Browse> getBrowse();
}
