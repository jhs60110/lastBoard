package com.example.Board.controller;

import com.example.Board.entity.Board;
import com.example.Board.entity.Comment;
import com.example.Board.entity.User;
import com.example.Board.service.BoardService;
import com.example.Board.service.CommentService;
import com.example.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public String saveComment(@ModelAttribute Comment comment, HttpServletRequest request) {
        commentService.saveComment(comment);

        return "redirect:" + request.getHeader("Referer");
    }

    @PutMapping("/{id}")
    public String updateComment(@ModelAttribute Comment comment, HttpServletRequest request, @PathVariable Long id) {
        commentService.updateComment(comment, id);

        return "redirect:" + request.getHeader("Referer");
    }

    @DeleteMapping("/{id}")
    public String deleteComment(HttpServletRequest request, @PathVariable Long id) {
        commentService.deleteComment(id);

        return "redirect:" + request.getHeader("Referer");
    }


}
