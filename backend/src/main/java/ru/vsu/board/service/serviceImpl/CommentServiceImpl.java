package ru.vsu.board.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.board.model.Comment;
import ru.vsu.board.repository.CommentRepository;
import ru.vsu.board.service.CommentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAll() {
        List<Comment> comments = new ArrayList<>();
        commentRepository.findAll().forEach(comments::add);
        return comments;
    }

    @Override
    public Comment getById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public Comment saveOrUpdate(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
