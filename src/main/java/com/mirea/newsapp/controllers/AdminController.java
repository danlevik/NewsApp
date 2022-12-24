package com.mirea.newsapp.controllers;


import com.mirea.newsapp.models.Person;
import com.mirea.newsapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private PersonService personService;

    @Autowired
    private TagService tagService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users")
    public String showUserList(@AuthenticationPrincipal Person person,
                               Model model){

        model.addAttribute("authority", person.getAuthorities().toString());
        model.addAttribute("userList", personService.getAllPersons());

        return "admin";
    }

    @PostMapping("/users")
    public String changeUserRole(Model model,
                                 @RequestParam(name = "personId") int personId,
                                 @RequestParam(name = "changeRole") String personRole){

        Person person = personService.getPersonById(personId);
        person.setRole(roleService.findRoleByName(personRole));
        personService.save(person);

        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete")
    public String deleteUser(Model model,
                             @RequestParam(name = "personId") int personId) {

        personService.deletePersonById(personId);

        return "redirect:/admin/users";
    }
}
