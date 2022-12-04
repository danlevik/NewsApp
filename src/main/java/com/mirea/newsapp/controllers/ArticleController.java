package com.mirea.newsapp.controllers;

import com.mirea.newsapp.models.Article;
import com.mirea.newsapp.models.Comment;
import com.mirea.newsapp.models.Person;
import com.mirea.newsapp.services.ArticleService;
import com.mirea.newsapp.services.CommentService;
import com.mirea.newsapp.services.PersonService;
import com.mirea.newsapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TagService tagService;

    @GetMapping("/news/{id}")
    public String articlePage(
            @AuthenticationPrincipal Person person,
            @PathVariable(value = "id") int id,
            Model model) {

        Article article = articleService.getArticleById(id);

        Person articleAuthor = personService.getPersonById(article.getAuthorId());

        List<String> articleParagraphs = List.of(article.getContent().split("\r?\n|\r"));


//        Проверка
        System.out.println(person.getAuthorities().toString());

        model.addAttribute("authority", person.getAuthorities().toString());
        model.addAttribute("articleTitle", article.getTitle());
        model.addAttribute("articleParagraphs", articleParagraphs);
        model.addAttribute("articleId", article.getId());

        model.addAttribute("articleTag", tagService.getTagById(article.getTagId()));
        model.addAttribute("articleAuthorName", articleAuthor.getName());
        model.addAttribute("articleAuthorSurname", articleAuthor.getSurname());
        model.addAttribute("readingTime", articleService.getReadingTimeInMinutes(article));

        List<Comment> commentList = commentService.getCommentsByArticleId(id);
        model.addAttribute("commentList", commentList);
        model.addAttribute("personService", personService);
        model.addAttribute("articleService", articleService);

        return "article";
    }

    @PostMapping("/news/{id}")
    public String postCommentOnArticle(
            Authentication authentication,
            @AuthenticationPrincipal Person person,
            @RequestParam(name = "commentContent") String commentContent,
            @PathVariable(value = "id") int id,
            Model model) {

        String personRole = personService.getUserRole(authentication);

        if (!personRole.equals("[GUEST]")){
            java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());

            Comment newComment = new Comment();

            newComment.setPersonId(person.getId());
            newComment.setArticleId(id);
            newComment.setContent(commentContent);
            newComment.setDate(currentDate);
            commentService.saveComment(newComment);
        }

        return "redirect:/news/" + id;
    }
}

