package com.mirea.newsapp.controllers;

import com.mirea.newsapp.models.Article;
import com.mirea.newsapp.models.Person;
import com.mirea.newsapp.models.Tag;
import com.mirea.newsapp.services.ArticleService;
import com.mirea.newsapp.services.CommentService;
import com.mirea.newsapp.services.PersonService;
import com.mirea.newsapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String newsPage(
            @AuthenticationPrincipal Person person,
            @RequestParam(name = "tagId", required = false) Integer tagId,
            @RequestParam(name = "searchRequest", required = false) String searchRequest,
            Model model
            ){


        model.addAttribute("authority", person.getAuthorities().toString());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("tagService", tagService);
        List<Article> articleList;

        if (searchRequest == null){
            if (tagId == null){
                articleList = articleService.getAllArticles();
            }
            else{
                articleList = articleService.getArticlesByTagId(tagId);
            }
        }
        else {
            if (tagId == null){
                articleList = articleService.findArticlesByTitle(searchRequest);
            }
            else{
                articleList = articleService.getArticlesByTagId(tagId).stream().filter(articleService.findArticlesByTitle(searchRequest)::contains)
                        .collect(Collectors.toList());;
            }

        }

        model.addAttribute("articleList", articleList);

        return "index";
    }


}
