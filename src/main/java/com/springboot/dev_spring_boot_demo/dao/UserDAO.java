package com.springboot.dev_spring_boot_demo.dao;

import com.springboot.dev_spring_boot_demo.entity.User;
import java.util.List;

public interface UserDAO {
    List<User> findAll();
    User findById(String username);
    User save(User user);
    void deleteById(String username);
}