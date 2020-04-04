package ru.vsu.board.service;


import ru.vsu.board.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAll();

    Post getById(Long id);

    Post saveOrUpdate(Post post);

    void delete(Long id);
}
