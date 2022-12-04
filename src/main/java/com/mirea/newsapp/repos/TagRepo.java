package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Integer> {
    Tag findById(int id);
    Long deleteById(int id);
    Tag findByName(String name);
}
