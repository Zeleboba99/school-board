package ru.vsu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ru.vsu.board.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
