package ru.vsu.board.service;

import ru.vsu.board.dto.CommentRequest;
import ru.vsu.board.dto.CommentResponse;
import ru.vsu.board.model.Comment;

import java.util.List;

public interface CommentService {
    List<CommentResponse> getAllByPost(Long post_id);

    Comment getById(Long id);

    Comment saveOrUpdate(CommentRequest comment,Long post_id, String username);

    void delete(Long id);
}
