package ru.vsu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.board.dto.CommentRequest;
import ru.vsu.board.dto.CommentResponse;
import ru.vsu.board.model.Comment;
import ru.vsu.board.service.CommentService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/posts/{post_id}/comments")
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public List<CommentResponse> getAllComments(@PathVariable("post_id") String post_id){
        return commentService.getAllByPost(Long.parseLong(post_id));
    }

    @PostMapping("/posts/{post_id}/comments")
    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public void addComment(@PathVariable("post_id") String id,@RequestBody CommentRequest comment){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        commentService.saveOrUpdate(comment,Long.parseLong(id),username);
    }


}
