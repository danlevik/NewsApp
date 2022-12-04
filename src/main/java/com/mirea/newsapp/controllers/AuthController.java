package com.mirea.newsapp.controllers;

import com.mirea.newsapp.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    private PersonService personService;

    @GetMapping("/signin")
    public String signIn() {
        return "signin";
    }

    @PostMapping("/signin")
    public String signInCreate(HttpServletRequest request,
                               @RequestParam(name = "username") String username,
                               @RequestParam(name = "password") String password,
                               Model model) {
        if (personService.loadUserByUsername(username) != null) {
            model.addAttribute("status", "user_exists");
            return "signin";
        }
        else {
            personService.create(username,password);
            authWithHttpServletRequest(request, username, password);
            return "redirect:/";
        }
    }

    public void authWithHttpServletRequest(HttpServletRequest request, String username, String password) {
        System.out.println(username);
        System.out.println(password);
        try {
            request.login(username, password);
        } catch (ServletException e) {
        }
    }
}
