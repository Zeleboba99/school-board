package ru.vsu.board.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.board.dto.MessageResponse;
import ru.vsu.board.dto.PostRequest;
import ru.vsu.board.dto.PostResponse;
import ru.vsu.board.model.User;
import ru.vsu.board.service.PostService;
import ru.vsu.board.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posts")
@Api
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;


    @GetMapping("")
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public Page<PostResponse> getAllPosts(@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size){
        return postService.getAll(page,size);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('STUDENT') or hasRole('TEACHER')")
    public ResponseEntity<?> getPostById(@PathVariable("id") String id){
        PostResponse postResponse = postService.getById(Long.parseLong(id));
        if (postResponse == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Post is not exists!"));
        }
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasRole('TEACHER')")
    public void addPost(@RequestBody PostRequest post){
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
       // User user = userRepository.findByUsername(UserName).orElse(null);
        postService.create(post,UserName);
    }

    @PutMapping("/{post_id}")
    @PreAuthorize("hasRole('TEACHER')")
    public void editPost(@PathVariable("post_id") Long post_id, @RequestBody PostRequest post){
        String UserName = SecurityContextHolder.getContext().getAuthentication().getName();
        // User user = userRepository.findByUsername(UserName).orElse(null);
        postService.update(post_id, post,UserName);
    }

    @DeleteMapping("/{post_id}")
    @PreAuthorize("hasRole('TEACHER')")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") String id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User author = userService.findByUsername(username);
        PostResponse post = postService.getById(Long.parseLong(id));
        if(!post.getUsername().equals(username)){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("You can delete only your own posts"));
        }
        postService.delete(Long.parseLong(id));
        return ResponseEntity.ok(new MessageResponse("Post was deleted successfully"));
    }
}
