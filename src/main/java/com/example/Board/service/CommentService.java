package com.example.Board.service;

import com.example.Board.entity.Board;
import com.example.Board.entity.Comment;
import com.example.Board.entity.User;
import com.example.Board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveComment(Comment comment, User userId, Board commentBoardId) {
        comment.setUser(userId);
        comment.setBoard(commentBoardId);
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment, User userId, Board boardId, Long id) {
        comment.setId(id);
        comment.setUser(userId);
        comment.setBoard(boardId);
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
