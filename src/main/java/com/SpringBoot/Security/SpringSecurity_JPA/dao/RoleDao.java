package com.SpringBoot.Security.SpringSecurity_JPA.dao;

import com.SpringBoot.Security.SpringSecurity_JPA.entity.Role;
import org.springframework.stereotype.Repository;


public interface RoleDao {
    public Role findRoleByName(String theRole);
}
