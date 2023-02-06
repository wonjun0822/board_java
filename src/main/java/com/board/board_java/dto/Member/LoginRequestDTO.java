package com.board.board_java.dto.Member;

public record LoginRequestDTO (
    String memberId,
    String pwd
) {
    public static LoginRequestDTO of(String memberId, String pwd) {
        return new LoginRequestDTO(memberId, pwd);
    }
}
