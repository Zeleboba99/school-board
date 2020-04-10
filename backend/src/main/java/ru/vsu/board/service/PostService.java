package ru.vsu.board.service;


import ru.vsu.board.dto.PostRequest;
import ru.vsu.board.dto.PostResponse;
import ru.vsu.board.model.Post;

import java.util.List;

public interface PostService {
    List<PostResponse> getAll();

    PostResponse getById(Long id);

    Post create(PostRequest post, String username);

    void delete(Long id);

    void update(Long post_id, PostRequest post, String userName);
}
