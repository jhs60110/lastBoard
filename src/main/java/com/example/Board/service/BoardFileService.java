package com.example.Board.service;

import com.example.Board.entity.Board;
import com.example.Board.entity.BoardFile;
import com.example.Board.repository.BoardFileRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardFileService {

    private final BoardFileRepository boardFileRepository;

    public Optional<BoardFile> findBoardFile(Long id) {

        return boardFileRepository.findById(id);
    }
    public void saveBoardFile(List<BoardFile> fileList, Board board) {
        for (BoardFile boardFile : fileList) {
            boardFile.setBoard(board);
            boardFileRepository.save(boardFile);
        }
    }
    public void deleteBoardFile(Long id) {
        boardFileRepository.deleteById(id);
    }

    public void downloadBoardFile(Long id, HttpServletResponse response) throws IOException {
        Optional<BoardFile> boardFile = this.findBoardFile(id);
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
}



