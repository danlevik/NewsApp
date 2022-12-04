package com.mirea.newsapp.services;

import com.mirea.newsapp.models.Article;
import com.mirea.newsapp.models.Comment;
import com.mirea.newsapp.repos.ArticleRepo;
import com.mirea.newsapp.repos.CommentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private CommentRepo commentRepo;

    @Autowired
    public CommentService(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public Comment getCommentById(int id){
        return commentRepo.findById(id);
    }

    public void deleteCommentById(int id) {
        commentRepo.deleteById(id);
    }

    public List<Comment> getCommentsByArticleId(int id){
        return commentRepo.findAllByArticleId(id);
    }

    public List<Comment> getCommentsByPersonId(int id){
        return commentRepo.findAllByPersonId(id);
    }

    public void saveComment(Comment comment) {
        commentRepo.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepo.delete(comment);
    }

}
