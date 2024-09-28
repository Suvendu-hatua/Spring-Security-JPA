package com.SpringBoot.Security.SpringSecurity_JPA.dao;

import com.SpringBoot.Security.SpringSecurity_JPA.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImp implements UserDao{
    private final EntityManager entityManager;

    public UserDaoImp(EntityManager entityManager){
        this.entityManager=entityManager;
    }
    @Override
    public User findByUserName(String userName) {
        TypedQuery<User> theQuery=entityManager.createQuery("from User where username=:uName and enabled=true",User.class);
        theQuery.setParameter("uName",userName);
        User user=null;
        try{
            user=theQuery.getSingleResult();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

        return user;
    }
}
