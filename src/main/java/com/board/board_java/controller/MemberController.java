package com.board.board_java.controller;

import com.board.board_java.Service.ArticleService;
import com.board.board_java.Service.MemberService;
import com.board.board_java.Service.PaginationService;
import com.board.board_java.domain.type.SearchType;
import com.board.board_java.dto.ArticleDto;
import com.board.board_java.dto.Jwt.JwtDTO;
import com.board.board_java.dto.Member.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("/login")
    public JwtDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String memberId = loginRequestDTO.memberId();
        String pwd = loginRequestDTO.pwd();

        JwtDTO tokenInfo = memberService.login(memberId, pwd);

        return tokenInfo;
    }
}
