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
import java.util.Date;
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
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();
        for (Post post : posts) {
            PostResponse postResponse = new PostResponse(
                    post.getId(),
                    post.getHeader(),
                    post.getText(),
                    post.getUser().getUsername(),
                    post.getCreatedAt());
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
                post.getUser().getUsername(),
                post.getCreatedAt()
        );
    }

    @Override
    public Post create(PostRequest post, String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        Post newPost = new Post();
        newPost.setHeader(post.getHeader());
        newPost.setText(post.getText());
        newPost.setUser(user);
        newPost.setCreatedAt(new Date());
        postRepository.save(newPost);
        return newPost;
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public void update(Long post_id, PostRequest post, String userName) {
        User user = userRepository.findByUsername(userName).orElse(null);
        Post existingPost = postRepository.findById(post_id).orElse(new Post());
        existingPost.setHeader(post.getHeader());
        existingPost.setText(post.getText());
        existingPost.setCreatedAt(new Date());
        existingPost.setUser(user);
        postRepository.save(existingPost);
    }
}
