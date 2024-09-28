package com.SpringBoot.Security.SpringSecurity_JPA.service;

import com.SpringBoot.Security.SpringSecurity_JPA.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);
}
