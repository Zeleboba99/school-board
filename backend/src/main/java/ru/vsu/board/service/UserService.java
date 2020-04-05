package ru.vsu.board.service;

import ru.vsu.board.dto.UserResponse;
import ru.vsu.board.model.User;

import java.util.List;

public interface UserService {
    Boolean existsByUsername(String username);

    void save(User user);

    User findByUsername(String username);

    List<UserResponse> findAll();

    void delete(long id);

    User findById(long id);
}
