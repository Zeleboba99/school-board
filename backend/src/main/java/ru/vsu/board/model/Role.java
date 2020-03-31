package ru.vsu.board.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @SequenceGenerator(name = "roleSeq", sequenceName = "ROLE_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleSeq")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}
