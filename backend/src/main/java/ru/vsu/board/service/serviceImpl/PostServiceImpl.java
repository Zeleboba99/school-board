package ru.vsu.board.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.board.dto.PostRequest;
import ru.vsu.board.dto.PostResponse;
import ru.vsu.board.model.Post;
import ru.vsu.board.model.User;
import ru.vsu.board.repository.PostRepository;
import ru.vsu.board.repository.UserRepository;
import ru.vsu.board.service.PostService;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<PostResponse> getAll() {
        List<PostResponse> responses = new ArrayList<>();
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            PostResponse postResponse = new PostResponse(
                    post.getId(),
                    post.getHeader(),
                    post.getText(),
                    post.getUser().getUsername());
            responses.add(postResponse);
        }

        return responses;
    }

    @Override
    public PostResponse getById(Long id) {

        Post post = postRepository.findById(id).orElse(null);
        if (post == null) {
            return null;
        }
        return new PostResponse(
                post.getId(),
                post.getHeader(),
                post.getText(),
                post.getUser().getUsername()
        );
    }

    @Override
    public Post saveOrUpdate(PostRequest post, String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        Post newPost = new Post();
        newPost.setHeader(post.getHeader());
        newPost.setText(post.getText());
        newPost.setUser(user);
        postRepository.save(newPost);
        return newPost;
    }

    @Override
    public void delete(Long id) {

    }
}
