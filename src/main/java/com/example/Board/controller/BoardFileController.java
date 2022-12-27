package com.example.Board.controller;

import com.example.Board.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class BoardFileController {
    private final BoardFileService boardFileService;

    @GetMapping("/download/{boardFileId}")
    public void downloadFile(HttpServletResponse response, @PathVariable Long boardFileId) throws IOException {
        boardFileService.downloadBoardFile(boardFileId, response);
    }

    @DeleteMapping("")
    public String deleteBoardFile(HttpServletRequest request ,@RequestParam("boardFileId") Long boardFileId) {
        boardFileService.deleteBoardFile(boardFileId);

        return "redirect:" + request.getHeader("Referer");
    }
}
