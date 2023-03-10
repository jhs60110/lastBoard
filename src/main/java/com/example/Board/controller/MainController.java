package com.example.Board.controller;

import com.example.Board.entity.Board;
import com.example.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final BoardService boardService;

    @GetMapping({"", "/search/list", "/page"})
    public String boardMain(Model model, @PageableDefault(page = 0,size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword) {
        Page<Board> boardPage = null;
        if(searchKeyword == null) {
            boardPage = boardService.findBoardList(pageable);
        }else {
            boardPage = boardService.findBoardList(searchKeyword, pageable);
        }

        int nowPage = boardPage.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, boardPage.getTotalPages());

        model.addAttribute("page", boardPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "layout/board/mainPage";
    }
}
