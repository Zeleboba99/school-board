package ru.vsu.board.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<PostResponse> getAll(int page, int size) {
        List<PostResponse> responses = new ArrayList<>();
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageRequest);
        int totalElements = (int) posts.getTotalElements();
        return new PageImpl<>(
                posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getHeader(),
                        post.getText(),
                        post.getUser().getUsername(),
                        post.getCreatedAt()))
                .collect(Collectors.toList())
                ,pageRequest,totalElements);
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
