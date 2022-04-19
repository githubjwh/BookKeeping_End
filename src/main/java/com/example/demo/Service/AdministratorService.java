package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.entity.Administrator;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;


@Service
public class AdministratorService {

    @Autowired
    private UserMapper userMapper;

    public int getCount(){
        return userMapper.getUserCount();
    }

    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    public User findUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public void deleteUserById(Integer id) {
        userMapper.deleteUser(id);
    }
}
