package ru.vsu.board.service;


import ru.vsu.board.dto.PostRequest;
import ru.vsu.board.dto.PostResponse;
import ru.vsu.board.model.Post;

import java.util.List;

public interface PostService {
    List<PostResponse> getAll();

    PostResponse getById(Long id);

    Post saveOrUpdate(PostRequest post, String username);

    void delete(Long id);
}
