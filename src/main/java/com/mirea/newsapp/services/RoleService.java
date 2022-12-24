package com.mirea.newsapp.services;

import com.mirea.newsapp.models.Person;
import com.mirea.newsapp.models.Role;
import com.mirea.newsapp.repos.PersonRepo;
import com.mirea.newsapp.repos.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private RoleRepo roleRepo;

    @Autowired
    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public Role findRoleByName(String name){
        return roleRepo.getRoleByName(name);
    }
}
