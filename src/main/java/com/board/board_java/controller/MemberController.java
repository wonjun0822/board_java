package com.board.board_java.controller;

import com.board.board_java.Service.MemberService;
import com.board.board_java.dto.Jwt.JwtDTO;
import com.board.board_java.dto.Member.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
