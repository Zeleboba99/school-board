package ru.vsu.board.service;


import org.springframework.data.domain.Page;
import ru.vsu.board.dto.PostRequest;
import ru.vsu.board.dto.PostResponse;
import ru.vsu.board.model.Post;

import java.util.List;

public interface PostService {
    Page<PostResponse> getAll(int page, int size);

    PostResponse getById(Long id);

    Post create(PostRequest post, String username);

    void delete(Long id);

    void update(Long post_id, PostRequest post, String userName);
}
