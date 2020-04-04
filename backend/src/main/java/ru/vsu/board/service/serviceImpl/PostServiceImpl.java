package ru.vsu.board.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.board.model.Post;
import ru.vsu.board.repository.PostRepository;
import ru.vsu.board.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post getById(Long id) {
        return null;
    }

    @Override
    public Post saveOrUpdate(Post post) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
