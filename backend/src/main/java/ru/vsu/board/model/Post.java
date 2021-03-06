package ru.vsu.board.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @SequenceGenerator(name = "postSeq", sequenceName = "POST_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "text")
    private String text;

    @Column(name = "created_at")
    private Date createdAt;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post",orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
