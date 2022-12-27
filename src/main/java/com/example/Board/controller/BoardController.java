package com.example.Board.controller;

import com.example.Board.service.*;
import com.example.Board.config.PrincipalDetails;
import com.example.Board.entity.Board;
import com.example.Board.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
@Slf4j
public class BoardController {

    private final UserService userService;
    private final BoardService boardService;
    private final BoardFileService boardFileService;
    private final FileHandler fileHandler;

    @PostMapping("")
    public String saveBoard(@ModelAttribute Board board, @RequestPart(value = "files", required = false) List<MultipartFile> boardFile) throws IOException {
        boardService.saveBoard(board);

        if (boardFile != null) {
            boardFileService.saveBoardFile(fileHandler.userFileUpload(boardFile), board);
        }

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String findBoard(Model model, Authentication authentication, @PathVariable Long id) {
        if (authentication != null) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            model.addAttribute("principalDetails", principalDetails);
            //viewBoard에서 userRole도 쓰고 있어서 한 번에 붙이는게 좋을것같았음
        }

        Board boardInfo = boardService.findWithRels(id);
        boardService.updateViews(boardInfo.getId());
        model.addAttribute("boardInfo", boardInfo);

        return "layout/board/viewBoard";
    }

    @GetMapping("/new")
    public String saveBoard(Model model, Authentication authentication) {
        User user = userService.findUserId(authentication);
        model.addAttribute("user", user);
        return "layout/board/makeBoard";
    }

    @GetMapping("/update/{id}")
    public String updateBoard(Model model, Board board) {
        Board boardInfo = boardService.findBoard(board);
        model.addAttribute("boardInfo", boardInfo);

        return "layout/board/updateBoard";
    }

    @PutMapping("/update")
    public String updateBoard(@ModelAttribute Board board, @RequestPart(value = "files", required = false) List<MultipartFile> boardFile) throws IOException{
        boardService.updateBoard(board);

        if (boardFile != null) {
            boardFileService.saveBoardFile(fileHandler.userFileUpload(boardFile), board);
        }

        return "redirect:/";
    }

    @DeleteMapping("")
    public String deleteBoard(@RequestParam("boardId") Long boardId) {
        boardService.deleteBoard(boardId);

        return "redirect:/";
    }

}