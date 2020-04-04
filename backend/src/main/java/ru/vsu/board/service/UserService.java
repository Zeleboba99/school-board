package ru.vsu.board.service;

import ru.vsu.board.model.User;

public interface UserService {
    Boolean existsByUsername(String username);

    void save(User user);
}
