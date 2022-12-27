package com.example.Board.controller;

import com.example.Board.entity.User;
import com.example.Board.repository.UserRepository;
import com.example.Board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {

        return "layout/authority/login";
    }

    @PostMapping("/login")
    public String loginSuccess() {

        return "layout/board/mainPage";
    }

    @GetMapping("/join")
    public String join() {

        return "layout/authority/join";
    }

    @PostMapping("/join")
    public String join(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "layout/authority/join";
        }

        model.addAttribute("users", userRepository.findAll());
        userService.saveUser(user);

        return "redirect:/account/login";
    }

}