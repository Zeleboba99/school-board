package ru.vsu.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.board.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
