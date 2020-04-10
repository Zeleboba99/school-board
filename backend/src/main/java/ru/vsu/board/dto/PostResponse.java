package ru.vsu.board.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PostResponse {
    private Long post_id;
    private String header;
    private String text;
    private Date created_at;

    public PostResponse(Long post_id, String header, String text, String username, Date created_at) {
        this.post_id = post_id;
        this.header = header;
        this.text = text;
        this.username = username;
        this.created_at = created_at;
    }

    private String username;
    //also list of comments, comments DTO too ofk


}
