package com.example.Board.service;

import com.example.Board.entity.Board;
import com.example.Board.entity.User;
import com.example.Board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void saveBoard(Board board, String boardTitle, String boardContent, User authId) {
        board.setTitle(boardTitle);
        board.setContent(boardContent);
        board.setUser(authId);
        boardRepository.save(board);
    }

    public void updateBoard(Board board, String boardId, User authId) {
        board.setUser(authId);
        board.setId(Long.valueOf(boardId));
        boardRepository.save(board);
    }

    public Board selectBoard(Board board) {

        return boardRepository.findById(board.getId()).get();
    }

    public int updateViews(Long id) {

        return this.boardRepository.updateViews(id);
    }

    public Board selectBoard(Long id) {

        return boardRepository.findById(id).get();
    }
    public Page<Board> searchBoardList(String SearchKeyword,Pageable pageable){

        return boardRepository.findByTitleContaining(SearchKeyword, pageable);
    }
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    public Board selectReferenceById(Long boardId) {

        return boardRepository.getReferenceById(boardId);
    }

    public Page<Board> selectBoardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

}
