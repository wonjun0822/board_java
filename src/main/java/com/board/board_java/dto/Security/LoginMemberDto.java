package com.board.board_java.dto.Security;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.Member;
import com.board.board_java.dto.Member.MemberDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public record LoginMemberDto (
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname
) implements UserDetails {
    public static LoginMemberDto of(String username, String password, Collection<? extends GrantedAuthority> authorities, String email, String nickname) {
        return new LoginMemberDto(username, password, authorities, email, nickname);
    }

    public MemberDto dto() {
        return MemberDto.of(
                username,
                password,
                email,
                nickname
        );
    }
    public Member toEntity() {
        return Member.of(
                username,
                password,
                email,
                nickname
        );
    }
    @Override
    public String getUsername() {
        return username;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // 계정 만료 여부 (true - 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부 (true - 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 (true - 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 되어 있는지 (true - 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
