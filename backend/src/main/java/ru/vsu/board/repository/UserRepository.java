package ru.vsu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.board.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
