package com.mirea.newsapp.repos;

import com.mirea.newsapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepo extends JpaRepository<Comment, Integer> {
    List<Comment> findAllByArticleId(int articleId);
    List<Comment> findAllByPersonId(int personId);

    Comment findById(int id);

    @Modifying
    @Query(value = "delete from comment WHERE comment.comment_id = ?1", nativeQuery = true)
    Integer deleteById(int id);


}
