package com.example.demo.dao;

import com.example.demo.model.Comment;
import com.example.demo.model.Vo.CommentVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CommentDao {

    @Select("select c.user_id,c.text,c.create_time,user.name,user.avatar_url " +
            "from comment c,customer user " +
            "where c.article_id=#{articleId} and c.user_id=user.id")
    @Results(
            value = {
                    @Result(column = "user_id",property = "userId",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "userName",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "avatar_url",property = "userAvatar",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "text",property = "text",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "create_time",property = "createTime",javaType = LocalDateTime.class,jdbcType = JdbcType.TIMESTAMP)
            }
    )
    public List<Comment> getCommentByArticle(@Param("articleId")Integer articleId);

    @Insert("insert into comment (user_id,article_id,text,create_time) values" +
            "(#{userId},#{articleId},#{desc},#{time})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public Integer addComment(CommentVo vo);

}
