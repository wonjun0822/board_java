package com.board.board_java.domain;

import com.board.board_java.dto.Member.MemberDto;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Table(indexes = {
        @Index(columnList = "memberId", unique = true),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "nickname", unique = true)
})
@Entity
public class Member {
    @Id
    @Setter @Column(nullable = false, length = 50) private String memberId;
    @Setter @Column(nullable = false, length = 20) private String pwd;
    @Setter @Column(nullable = false, length = 20) private String email;
    @Setter @Column(nullable = false, length = 20) private String nickname;

    protected Member() {}

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
        if (!(o instanceof Member that)) return false;
        return this.getMemberId() != null && this.getMemberId().equals(that.getMemberId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMemberId());
    }
}
