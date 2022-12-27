package com.example.Board.service;

import com.example.Board.entity.Board;
import com.example.Board.entity.Comment;
import com.example.Board.entity.User;
import com.example.Board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment,  Long id) {
        comment.setId(id);
        commentRepository.save(comment);
    }

//    public List<Comment> findBoard(Long id) {
//
//        return commentRepository.findByBoardId(id);
//    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
