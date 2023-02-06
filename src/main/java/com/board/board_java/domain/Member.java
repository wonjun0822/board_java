package com.board.board_java.domain;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "memberId", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "nickname", unique = true)
})
@Entity
public class Member implements UserDetails {
    @Id
    @Setter @Column(nullable = false, length = 50) private String memberId;
    @Setter @Column(nullable = false, length = 20) private String pwd;
    @Setter @Column(nullable = false, length = 20) private String email;
    @Setter @Column(nullable = false, length = 20) private String nickname;

    protected Member() {}

    //    @Override
    //    public Collection<? extends GrantedAuthority> getAuthorities() {
    //        return this.roles.stream()
    //                .map(SimpleGrantedAuthority::new)
    //                .collect(Collectors.toList());
    //    }

    private Member(String memberId, String pwd, String email, String nickname) {
        this.memberId = memberId;
        this.pwd = pwd;
        this.email = email;
        this.nickname = nickname;
    }

    public static Member of(String memberId, String pwd, String email, String nickname) {
        return new Member(memberId, pwd, email, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member member)) return false;
        return memberId == member.memberId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
