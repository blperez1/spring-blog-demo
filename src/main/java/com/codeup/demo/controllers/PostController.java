package com.codeup.demo.controllers;

import com.codeup.demo.models.Post;
import com.codeup.demo.models.User;
import com.codeup.demo.repos.PostRepository;
import com.codeup.demo.repos.UserRepository;
import com.codeup.demo.services.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(Model model, @PathVariable long id) {
        model.addAttribute("posts", postDao.getOne(id));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String viewCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String createPost(@ModelAttribute Post postToBeSaved) {
        User user = userDao.getOne(1L);
        postToBeSaved.setOwner(user);
        Post dbPost = postDao.save(postToBeSaved);
        emailService.prepareAndSend(dbPost, "add has been created", "you can find it with the id of " + dbPost.getId());
        return "redirect:/posts/";
    }

    @GetMapping("/posts/{id}/edit")
    public String viewEditPostForm(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getOne(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@ModelAttribute Post postToBeUpdated) {
        User user = userDao.getOne(1L); // a user obj coming from a session
        postToBeUpdated.setOwner(user);
        postDao.save(postToBeUpdated);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id) {
        System.out.println("Does this run?");
        postDao.deleteById(id);
        return "redirect:/posts";
    }


}
