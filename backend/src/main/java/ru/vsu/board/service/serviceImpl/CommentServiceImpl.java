package ru.vsu.board.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.vsu.board.dto.CommentRequest;
import ru.vsu.board.dto.CommentResponse;
import ru.vsu.board.dto.PostResponse;
import ru.vsu.board.model.Comment;
import ru.vsu.board.model.ERole;
import ru.vsu.board.model.User;
import ru.vsu.board.repository.CommentRepository;
import ru.vsu.board.repository.PostRepository;
import ru.vsu.board.repository.UserRepository;
import ru.vsu.board.service.CommentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

//    @Override
//    public List<CommentResponse> getAllByPost(Long id) {
//        List<CommentResponse> responses = new ArrayList<>();
//        List<Comment> comments = new ArrayList<>();
//        commentRepository.getCommentsByPostId(id).forEach(comments::add);
//        for (Comment comment : comments) {
//            CommentResponse commentResponse = new CommentResponse(
//                    comment.getId(),
//                    comment.getText(),
//                    comment.getUser().getUsername(),
//                    comment.getPost().getId()
//            );
//            responses.add(commentResponse);
//        }
//        return responses;
//    }

    @Override
    public Page<CommentResponse> getAllByPost(int page, int size, Long post_id) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Comment> comments = commentRepository.getCommentsByPostId(post_id,pageRequest);
        int totalElements = (int) comments.getTotalElements();
        return new PageImpl<>(
                comments.stream()
                        .map(comment -> new CommentResponse(
                                comment.getId(),
                                comment.getText(),
                                comment.getUser().getUsername(),
                                comment.getPost().getId()
                                ))
                        .collect(Collectors.toList())
                ,pageRequest,totalElements);
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment saveOrUpdate(CommentRequest comment, Long post_id, String username) {
        Comment newComment = new Comment();
        newComment.setText(comment.getText());
        newComment.setPost(postRepository.getOne(post_id));
        newComment.setUser(userRepository.findByUsername(username).orElse(null));
        commentRepository.save(newComment);
        return newComment;
    }

    @Override
    public void delete(Long comm_id) {
        commentRepository.deleteById(comm_id);
    }
}
