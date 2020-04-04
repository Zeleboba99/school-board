package ru.vsu.board.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostRequest {

    @NotBlank
    private String header;

    @NotBlank
    private String text;
}
