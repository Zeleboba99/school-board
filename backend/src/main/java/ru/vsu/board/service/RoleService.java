package ru.vsu.board.service;

import ru.vsu.board.model.ERole;
import ru.vsu.board.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}
