package com.example.Board.service;

import com.example.Board.entity.Board;
import com.example.Board.entity.BoardFile;
import com.example.Board.repository.BoardFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardFileService {

    private final BoardFileRepository boardFileRepository;

    public List<BoardFile> selectBoard(Long id) {

        return boardFileRepository.findByBoardId(id);
    }

    public Optional<BoardFile> selectBoardFile(Long id) {

        return boardFileRepository.findById(id);
    }
    public void saveBoardFile(List<BoardFile> fileList, Board board) {

        for (BoardFile boardFile : fileList) {
            boardFile.setBoard(board);
            boardFileRepository.save(boardFile);
        }
    }
}



