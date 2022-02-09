package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Mapper
@Repository
public interface UserMapper {
	
	/**
     * 查询用户名是否存在，若存在，不允许注册
     * @param username
     */
	@Select(value = "select u.username,u.password from user u where u.username=#{username}")
	User getUserByUsername(@Param("username") String username);
	
	/**
     * 注册  插入一条user记录
     * @param user
     */
    @Insert("insert into user values(#{id},#{username},#{password},#{disk_name},#{disk_password})")
    void regist(User user);
    
    /**
     * 登录
     * @param user
     */
    @Select("select u.id from user u where u.username = #{username} and password = #{password}")
    Integer login(String username, String password);
}
