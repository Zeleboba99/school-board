package ru.vsu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.board.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
