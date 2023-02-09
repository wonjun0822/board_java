package com.board.board_java.dto.Member;

import com.board.board_java.domain.Member;

public record MemberDto (
    String memberId,
    String pwd,
    String email,
    String nickname
) {
    public static MemberDto of(String memberId, String pwd, String email, String nickname) {
        return new MemberDto(memberId, pwd, email, nickname);
    }

    public static MemberDto from(Member entity) {
        return new MemberDto(
                entity.getMemberId(),
                entity.getPwd(),
                entity.getEmail(),
                entity.getNickname()
        );
    }

    public Member toEntity() {
        return Member.of(
                memberId,
                pwd,
                email,
                nickname
        );
    }
}
