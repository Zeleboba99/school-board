package ru.vsu.board.service;

import org.springframework.data.domain.Page;
import ru.vsu.board.dto.CommentRequest;
import ru.vsu.board.dto.CommentResponse;
import ru.vsu.board.model.Comment;

import java.util.List;

public interface CommentService {
    Page<CommentResponse> getAllByPost(int page, int size, Long post_id);

    Comment getById(Long id);

    Comment saveOrUpdate(CommentRequest comment,Long post_id, String username);

    void delete(Long comment_id);
}
