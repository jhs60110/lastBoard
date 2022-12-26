package com.example.Board.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import lombok.Data;
import javax.persistence.Entity;

@Data
@Entity
@Getter
@Setter
public class BoardFile extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.MERGE)
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private String originalFileName;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String savedFileName;

    private Long fileSize;

}
