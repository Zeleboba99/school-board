package ru.vsu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.board.dto.PostRequest;
import ru.vsu.board.dto.PostResponse;
import ru.vsu.board.service.PostService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public List<PostResponse> getAllPosts(){
        return postService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    public PostResponse getPostById(@PathVariable("id") String id){
        return postService.getById(Long.parseLong(id));
    }
    @PostMapping("")
    @PreAuthorize("hasRole('TEACHER')")
    public void addPost(@RequestBody PostRequest post){
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
       // User user = userRepository.findByUsername(UserName).orElse(null);
        postService.saveOrUpdate(post,UserName);
    }
}
