package ru.vsu.board.service;

import ru.vsu.board.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();

    Comment getById(Long id);

    Comment saveOrUpdate(Comment comment);

    void delete(Long id);
}
