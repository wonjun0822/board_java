package com.board.board_java.dto.Member;

public record LoginRequestDTO (
    String Id,
    String pwd
) {
    public static LoginRequestDTO of(String Id, String pwd) {
        return new LoginRequestDTO(Id, pwd);
    }
}
