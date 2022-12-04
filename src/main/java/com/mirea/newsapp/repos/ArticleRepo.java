package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Integer> {
    Article findById(int id);
    Long deleteById(int id);

    List<Article> findAllByTagId(int tagId);

//    Поиск
    List<Article> findAllByTitleLike(String query);

}
