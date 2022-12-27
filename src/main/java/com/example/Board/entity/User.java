package com.example.Board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.*;

@Entity
@Setter
@Getter
public class User extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "아이디를 입력해주세요")
    @Column(length = 10, unique = true)
    private String userId;

    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Board> board = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Comment> comment = new ArrayList<>();

    @NotEmpty(message = "이름을 입력해주세요")
    @Column(length = 10)
    private String userName;

    @NotEmpty(message = "비밀번호를 입력해주세요")
    @Column(length = 1000)
    @JsonIgnore
    private String password;

    @Column(nullable = false, length = 10)
    private String role;

}