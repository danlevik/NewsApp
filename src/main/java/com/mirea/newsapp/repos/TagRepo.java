package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepo extends JpaRepository<Tag, Integer> {
    Tag findById(int id);

    @Modifying
    @Query(value = "delete from tag WHERE tag.tag_id = ?1", nativeQuery = true)
    Integer deleteById(int id);

    Tag findByName(String name);
}
