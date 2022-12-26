package com.example.Board.service;

import com.example.Board.entity.Board;
import com.example.Board.entity.User;
import com.example.Board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void saveBoard(Board board, User userId) {
        board.setUser(userId);
        boardRepository.save(board);
    }

    public void updateBoard(Board board, String boardId, User userId) {
        board.setUser(userId);
        board.setId(Long.valueOf(boardId));
        boardRepository.save(board);
    }

    public Board findBoard(Board board) {

        return boardRepository.findById(board.getId()).get();
    }

    public int updateViews(Long id) {

        return this.boardRepository.updateViews(id);
    }

    public Board findWithRels(Long boardId) {

        return boardRepository.findWithRels(boardId);
    }
    public Page<Board> findBoardList(String SearchKeyword,Pageable pageable){

        return boardRepository.findByTitleContaining(SearchKeyword, pageable);
    }
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public Board findReferenceById(Long boardId) {

        return boardRepository.getReferenceById(boardId);
    }


    public Page<Board> findBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }



}
