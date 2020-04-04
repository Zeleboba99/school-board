package ru.vsu.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponse {
    private Long id;
    private String text;
    private String username;
    private Long post_id;

    public CommentResponse(Long id, String text, String username, Long post_id) {
        this.id = id;
        this.text = text;
        this.username = username;
        this.post_id = post_id;
    }
}
