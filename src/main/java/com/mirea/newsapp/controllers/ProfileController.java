package com.mirea.newsapp.controllers;


import com.mirea.newsapp.models.Article;
import com.mirea.newsapp.models.Comment;
import com.mirea.newsapp.models.Person;
import com.mirea.newsapp.services.ArticleService;
import com.mirea.newsapp.services.CommentService;
import com.mirea.newsapp.services.PersonService;
import com.mirea.newsapp.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class ProfileController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TagService tagService;


    @GetMapping("/profile")
    public String profilePage(
            @AuthenticationPrincipal Person person,
            Model model) {

        model.addAttribute("authority", person.getAuthorities().toString());
        model.addAttribute("nickname", person.getUsername());
        model.addAttribute("firstname", person.getName());
        model.addAttribute("surname", person.getSurname());
        model.addAttribute("tags", tagService.getAllTags());
        model.addAttribute("articleList", articleService.findArticlesByAuthorId(person.getId()));
        model.addAttribute("tagService", tagService);

        return "profile";
    }

    @PostMapping("/profile")
    public String changeProfile(
            @AuthenticationPrincipal Person person,
            @RequestParam(name = "personName") String personName,
            @RequestParam(name = "personSurname") String personSurname,
            @RequestParam(name = "personUsername") String personUsername,
            @RequestParam(name = "personPassword") String personPassword,
            Model model) {

        personService.update(person, personName, personSurname, personUsername, personPassword);

        return "redirect:/login";

    }

//    Чтобы работал DeleteMapping нужно отдельно писать отправку DELETE в js
    @PostMapping("/articleDelete")
    public String deleteArticle(Authentication authentication,
                                @RequestParam(name = "articleId") int articleId){

        articleService.deleteArticleById(articleId);

        return "redirect:/profile";
    }

    @PostMapping("/article")
    public String postArticle(Authentication authentication,
                              @AuthenticationPrincipal Person person,
                              @RequestParam(name = "articleTitle") String articleTitle,
                              @RequestParam(name = "articleContent") String articleContent,
                              @RequestParam(name = "articleTag") int articleTag,
                              @RequestParam(name = "articleImage") MultipartFile articleImage,
                              Model model) throws IOException {

        String personRole = personService.getUserRole(authentication);

        String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img/";


        String articleImageLink;

        if (articleImage != null){
            String filePath = UPLOAD_DIRECTORY + articleImage.getOriginalFilename();
            articleImage.transferTo(new File(filePath));
            articleImageLink = articleImage.getOriginalFilename();
        }
        else{
            articleImageLink = "default.png";
        }

        if (!personRole.equals("[GUEST]")){
            java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());

            Article newArticle = new Article();

            newArticle.setTitle(articleTitle);
            newArticle.setContent(articleContent);
            newArticle.setAuthorId(person.getId());
            newArticle.setDate(currentDate);
            newArticle.setTagId(articleTag);
            newArticle.setPictureLink(articleImageLink);
            articleService.saveArticle(newArticle);
        }

        return "redirect:/profile";
    }
}
