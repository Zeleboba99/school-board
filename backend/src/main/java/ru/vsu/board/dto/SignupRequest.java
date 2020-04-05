package ru.vsu.board.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class SignupRequest {
    @NotBlank
    private String username;

    private Set<String> role;

    @NotBlank
    private String password;

}
