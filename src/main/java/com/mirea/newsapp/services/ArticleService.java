package com.mirea.newsapp.services;

import com.mirea.newsapp.models.Article;
import com.mirea.newsapp.repos.ArticleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private ArticleRepo articleRepo;

    @Autowired
    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public Article getArticleById(int id){
        return articleRepo.findById(id);
    }

    public List<Article> getAllArticles(){
        return articleRepo.findAll();
    }

    public List<Article> getArticlesByTagId(int id){
        return articleRepo.findAllByTagId(id);
    }

    public List<Article> findArticlesByTitle(String query){
        return articleRepo.findAllByTitleLike("%" + query + "%");
    }

    public void deleteArticleById(int id) {
        articleRepo.deleteById(id);
    }

    public void saveArticle(Article article) {
        articleRepo.save(article);
    }

    public void deleteArticle(Article article) {
        articleRepo.delete(article);
    }


    public Integer getReadingTimeInMinutes (Article article){
        return article.getContent().length() / 600 + 1;
    }
}
