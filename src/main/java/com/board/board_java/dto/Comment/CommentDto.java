package com.board.board_java.dto.Comment;

import com.board.board_java.domain.Comment;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.board.board_java.domain.Comment} entity
 */
public record CommentDto (
        String comment,
        String createId,
        LocalDateTime createDate
) {
    public static CommentDto of (String comment, String createId, LocalDateTime createDate) {
        return new CommentDto(comment, createId, createDate);
    }

    public static CommentDto from (Comment entity) {
        return new CommentDto (
                entity.getComment(),
                entity.getCreateBy(),
                entity.getCreateAt()
        );
    }
}