package com.example.demo.dao;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Customer;
import com.example.demo.model.CustomerSimple;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository("CustomerDao")
@Mapper
public interface CustomerDao {
    //获取全部用户
    @Select("select * from customer")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "back_url",property = "backUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "type",property = "type",javaType = Byte.class,jdbcType = JdbcType.TINYINT),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public List<CustomerSimple> getAll();

    //获取全部用户
    @Select("select * from customer where name like '%${key}%' and state=0")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "back_url",property = "backUrl",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "type",property = "type",javaType = Byte.class,jdbcType = JdbcType.TINYINT),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    public List<CustomerSimple> searchAll(@Param("key") String key);

    //获得某个人的全部信息
        @Select("select * from customer where id=#{id} and state=0")
        @Results(
                value = {
                        @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                        @Result(column = "name",property = "name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                        @Result(column = "password",property = "password",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                        @Result(column = "introduce",property = "introduce",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                        @Result(column = "email",property = "email",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                        @Result(column = "phone",property = "phone",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                        @Result(column = "backUrl",property = "back_url",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                        @Result(column = "avatarUrl",property = "avatar_url",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                        @Result(column = "type",property = "type",javaType = Byte.class,jdbcType = JdbcType.TINYINT),
                        @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
                }
        )
        public Customer getCustomerById(@Param("id") Integer id);
        //修改用户信息

    @Update("update customer set " +
            "password=#{customer.password}," +
            "introduce=#{customer.introduce}," +
            "email=#{customer.email}," +
            "phone=#{customer.phone}," +
            "back_url=#{customer.backUrl}," +
            "avatar_url=#{customer.avatarUrl}," +
            "type=#{customer.type}," +
            "state=#{customer.state}" +
            " where id=#{customer.id}")
    public void updateCustomer(@Param("customer") Customer customer);

    public List<Customer> selectCustomer(Customer sample);

    @Insert("insert into customer (name,password,email,phone,type,state) values " +
            "(#{name},#{password},#{email}," +
            "#{phone},0,0)")
    public void addCustomer(Customer customer);

    //管理员相关
    @Select("select * from customer")
    @Results(
            value = {
                    @Result(column = "id",property = "id",javaType = Integer.class,jdbcType = JdbcType.INTEGER),
                    @Result(column = "name",property = "name",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "password",property = "password",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "introduce",property = "introduce",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "email",property = "email",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "phone",property = "phone",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "backUrl",property = "back_url",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "avatarUrl",property = "avatar_url",javaType = String.class,jdbcType = JdbcType.VARCHAR),
                    @Result(column = "type",property = "type",javaType = Byte.class,jdbcType = JdbcType.TINYINT),
                    @Result(column = "state",property = "state",javaType = Byte.class,jdbcType = JdbcType.TINYINT)
            }
    )
    List<Customer> adminGetAll();

    @Update("update customer set state=#{state} " +
            "where id=#{id}")
    void changeCustomerState(@Param("state") Integer state, @Param("id") Integer id);
}
