package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Role getRoleByName(String name);
}
