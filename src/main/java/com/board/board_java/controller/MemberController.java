package com.board.board_java.controller;

import com.board.board_java.Service.MemberService;
import com.board.board_java.dto.Jwt.JwtDTO;
import com.board.board_java.dto.Member.LoginRequestDTO;
import com.board.board_java.dto.Security.LoginMemberDto;
import com.board.board_java.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Transactional
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping(value = "/login", produces = "application/json")
    public JwtDTO login(@RequestBody LoginRequestDTO loginRequestDTO) {
        String memberId = loginRequestDTO.Id();
        String pwd = loginRequestDTO.pwd();

        JwtDTO tokenInfo = memberService.login(memberId, pwd);

        return tokenInfo;
    }

    @Transactional(readOnly = true)
    @GetMapping(value = "member", produces = "application/json")
    public LoginMemberDto getMemberInfo(@AuthenticationPrincipal LoginMemberDto loginMemberDto) {
        return loginMemberDto;
    }
}
