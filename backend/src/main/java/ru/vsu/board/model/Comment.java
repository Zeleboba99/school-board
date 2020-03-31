package ru.vsu.board.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @SequenceGenerator(name = "commentSeq", sequenceName = "COMMENT_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "commentSeq")
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
