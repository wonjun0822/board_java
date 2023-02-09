package com.board.board_java.Service;

import com.board.board_java.domain.Member;
import com.board.board_java.dto.Security.LoginMemberDto;
import com.board.board_java.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = memberRepository.findById(username)
                //.map(this::createLoginMemberDto)
                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));

        return new LoginMemberDto(
                user.getMemberId(),
                passwordEncoder.encode(user.getPwd()),
                List.of(new SimpleGrantedAuthority("DEFAULT")),
                user.getEmail(),
                user.getNickname()
        );
    }

    private UserDetails createLoginMemberDto(Member member) {
        var user = User.builder()
                .username(member.getMemberId())
                .password(passwordEncoder.encode(member.getPwd()))
                //.roles(member.getRoles().toArray(new String[0]))
                .roles(String.valueOf(new SimpleGrantedAuthority("DEFAULT"))) // 권한이 없는 경우 기본 권한을 꼭 넣어줘야함
                .build();

        return new LoginMemberDto(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities(),
                member.getEmail(),
                member.getNickname()
        );
    }
}