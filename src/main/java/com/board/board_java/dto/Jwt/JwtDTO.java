package com.board.board_java.dto.Jwt;

import lombok.Builder;

@Builder
public record JwtDTO (
    String accessToken,
    String refreshToken
) {}
