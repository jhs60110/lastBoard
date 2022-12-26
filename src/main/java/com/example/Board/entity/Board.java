package com.example.Board.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Entity
@Getter
@Setter
public class Board extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") //실제 DB ID
    private User user;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<BoardFile> boardFiles;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 600)
    private String content;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int views;

}
