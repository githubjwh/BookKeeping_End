package com.example.demo.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Mapper
@Repository
public interface UserMapper {
	
	/**
     * 根据用户名获取用户
     * @param username
     */
	@Select(value = "select * from user u where u.username=#{username}")
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
    
    /**
     * 根据id获取用户
     * @param id
     */
    @Select(value = "select * from user u where u.id=#{id}")
	User getUserById(@Param("id") Integer id);
    
    /**
     * 根据id删除用户
     * @param id
     */
    @Delete("delete from user where id=#{id}") 
    public void deleteUser(@Param("id") Integer id);
    
    /**
     * 更新用户名
     * @param username
     * @param id
     */
    @Update("update user set username=#{username} where id=#{id}")
    void updateUsername(@Param("username") String username, @Param("id") Integer id);
    
    /**
     * 更新用户密码
     * @param password
     * @param id
     */
    @Update("update user set password=#{password} where id=#{id}")
    void updatePassword(@Param("password") String password, @Param("id") Integer id);
    
    /**
     * 更新云盘账号
     * @param disk_name
     * @param id
     */
    @Update("update user set diskname=#{disk_name} where id=#{id}")
    void updateDiskName(@Param("disk_name") String disk_name, @Param("id") Integer id);
    
    /**
     * 更新云盘密码
     * @param disk_password
     * @param id
     */
    @Update("update user set diskpassword=#{disk_password} where id=#{id}")
    void updateDiskPassword(@Param("disk_password") String disk_password, @Param("id") Integer id);
}
