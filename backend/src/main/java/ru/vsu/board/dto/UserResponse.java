package ru.vsu.board.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Long user_id;
    private String username;
    private String password;
    private String role;
}
