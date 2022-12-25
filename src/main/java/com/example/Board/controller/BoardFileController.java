package com.example.Board.controller;

import com.example.Board.entity.BoardFile;
import com.example.Board.service.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/files")
public class BoardFileController {

    private final BoardFileService boardFileService;

    @GetMapping("/download/{boardFileId}")
    public void downloadFile(HttpServletResponse response, @PathVariable Long boardFileId) throws IOException {

        Optional<BoardFile> boardFile = boardFileService.findBoardFile(boardFileId);
        String savedFileName = boardFile.get().getSavedFileName();
        String originalFileName = boardFile.get().getOriginalFileName();
        String path =savedFileName;
        byte[] fileByte = FileUtils.readFileToByteArray(new File(path));

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName, "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");

        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    @DeleteMapping("")
    public String deleteBoardFile(HttpServletRequest request ,@RequestParam("boardFileId") Long boardFileId) {
        boardFileService.deleteBoardFile(boardFileId);

        return "redirect:" + request.getHeader("Referer");
    }
}
