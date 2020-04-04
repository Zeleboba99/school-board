package ru.vsu.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.vsu.board.model.Comment;
import ru.vsu.board.service.CommentService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;




    @GetMapping("/comments")
    public List<Comment> getAllComments(){
        return commentService.getAll();
    }

    @PostMapping("/comments")
    public void addComment(@RequestBody Comment comment){
        commentService.saveOrUpdate(comment);
    }


}
