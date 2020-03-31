package ru.vsu.board.model;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private Long id;

    @NonNull
    @Column(name = "username")
    private String username;

    @NonNull
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                name = "role_id", referencedColumnName = "id"
            )
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;
}
