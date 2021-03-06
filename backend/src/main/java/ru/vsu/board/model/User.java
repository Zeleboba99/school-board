package ru.vsu.board.model;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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

    public User() {
    }

    public User(String username,String Password) {
        this.username = username;
        this.password = Password;
    }

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Post> posts;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments;
}
