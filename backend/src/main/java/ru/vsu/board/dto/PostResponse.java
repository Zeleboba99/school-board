package ru.vsu.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {
    private Long post_id;
    private String header;
    private String text;

    public PostResponse(Long post_id, String header, String text, String username) {
        this.post_id = post_id;
        this.header = header;
        this.text = text;
        this.username = username;
    }

    private String username;
    //also list of comments, comments DTO too ofk


}
