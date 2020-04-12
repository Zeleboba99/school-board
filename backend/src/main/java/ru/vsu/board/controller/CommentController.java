package ru.vsu.board.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.board.dto.CommentRequest;
import ru.vsu.board.dto.CommentResponse;
import ru.vsu.board.dto.MessageResponse;
import ru.vsu.board.model.Comment;
import ru.vsu.board.model.ERole;
import ru.vsu.board.model.User;
import ru.vsu.board.service.CommentService;
import ru.vsu.board.service.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Api
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    @GetMapping("/posts/{post_id}/comments")
//    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public Page<CommentResponse> getAllComments(@PathVariable("post_id") String post_id,@RequestParam(defaultValue = "0") int page, @RequestParam("size") int size){
        return commentService.getAllByPost(page,size,Long.parseLong(post_id));
    }

    @PostMapping("/posts/{post_id}/comments")
//    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public void addComment(@PathVariable("post_id") String id,@RequestBody CommentRequest comment){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        commentService.saveOrUpdate(comment,Long.parseLong(id),username);
    }
    @DeleteMapping("/posts/{post_id}/comments/{comment_id}")
//    @PreAuthorize("hasAnyRole('STUDENT','TEACHER')")
    public ResponseEntity<?> deleteComment(@PathVariable("post_id") String id, @PathVariable("comment_id") String comment_id){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User author = userService.findByUsername(username);
        Comment comment = commentService.getById(Long.parseLong(comment_id));
        if(author.getRoles().stream().anyMatch(item-> item.getName() == ERole.ROLE_STUDENT) && comment.getUser() != author){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Students haven't got permission to do that"));
        }
        commentService.delete(Long.parseLong(comment_id));
        return ResponseEntity.ok(new MessageResponse("Comment was deleted successfully"));
    }


}
