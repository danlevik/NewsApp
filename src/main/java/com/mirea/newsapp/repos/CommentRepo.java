package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByArticleId(int articleId);
    List<Comment> findAllByPersonId(int personId);

    Comment findById(int id);

    Long deleteById(int id);


}
