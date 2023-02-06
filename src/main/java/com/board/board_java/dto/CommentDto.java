package com.board.board_java.dto;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.Comment;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.board.board_java.domain.Comment} entity
 */
public record CommentDto(
        String createId,
        LocalDateTime createDate,
        String comment
) {
    public static CommentDto of(String createId, LocalDateTime createDate, String comment) {
        return new CommentDto(createId, createDate,comment);
    }

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                entity.getCreateBy(),
                entity.getCreateAt(),
                entity.getComment()
        );
    }
}