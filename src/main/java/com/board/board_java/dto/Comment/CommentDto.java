package com.board.board_java.dto.Comment;

import com.board.board_java.domain.Comment;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.board.board_java.domain.Comment} entity
 */
public record CommentDto (
        Long articleId,
        Long commentId,
        String comment,
        String nickname,
        LocalDateTime createAt
) {
    public static CommentDto of (Long articleId, Long commentId, String comment, String nickname, LocalDateTime createAt) {
        return new CommentDto(articleId, commentId, comment, nickname, createAt);
    }

    public static CommentDto from (Comment entity) {
        return new CommentDto (
                entity.getArticle().getId(),
                entity.getId(),
                entity.getComment(),
                entity.getMember().getNickname(),
                entity.getCreateAt()
        );
    }
}