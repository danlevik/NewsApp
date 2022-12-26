package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Integer> {
    Article findById(int id);

    @Modifying
    @Query(value = "delete from article WHERE article.article_id = ?1", nativeQuery = true)
    Integer deleteById(int id);

    List<Article> findAllByTagId(int tagId);

    List<Article> findAllByAuthorId(int authorId);

//    Поиск
    List<Article> findAllByTitleLike(String query);

}
