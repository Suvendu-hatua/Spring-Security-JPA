package com.SpringBoot.Security.SpringSecurity_JPA.dao;

import com.SpringBoot.Security.SpringSecurity_JPA.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImp implements RoleDao {
    private final EntityManager entityManager;

    public RoleDaoImp(EntityManager entityManager){
        this.entityManager=entityManager;
    }


    @Override
    public Role findRoleByName(String theRole) {
       TypedQuery<Role> theQuery =entityManager.createQuery("from Role where name=:theName",Role.class);

       theQuery.setParameter("theName",theRole);
       Role role=null;
       try{
           role=theQuery.getSingleResult();
       }catch (Exception e){
           System.out.println(e.getMessage());
       }
        return role;
    }
}
