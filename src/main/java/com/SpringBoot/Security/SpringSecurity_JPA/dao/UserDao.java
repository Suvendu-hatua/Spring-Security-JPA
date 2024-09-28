package com.SpringBoot.Security.SpringSecurity_JPA.dao;

import com.SpringBoot.Security.SpringSecurity_JPA.entity.User;


public interface UserDao {
    public User findByUserName(String userName);
}
