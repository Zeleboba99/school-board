package ru.vsu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.board.model.Post;
import ru.vsu.board.model.User;
import ru.vsu.board.repository.UserRepository;
import ru.vsu.board.service.PostService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return postService.getAll();
    }

    @PostMapping("/posts")
    public void addPost(@RequestBody Post post){
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(UserName).orElse(null);
        post.setUser(user);
        postService.saveOrUpdate(post);
    }
}
