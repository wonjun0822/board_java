package com.board.board_java.dto.Article;

import com.board.board_java.domain.Article;
import com.board.board_java.dto.Comment.CommentDto;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link com.board.board_java.domain.Article} entity
 */
public record ArticleDetailDto (
        Long id,
        String title,
        String content,
        int viewCount,
        String hashTag,
        String nickname,
        LocalDateTime createAt,
        Set<CommentDto> commentDto
) {
    public static ArticleDetailDto of (Long id, String title, String content, int viewCount, String hashTag, String nickname, LocalDateTime createAt, Set<CommentDto> commentDto) {
        return new ArticleDetailDto (id, title, content, viewCount, hashTag, nickname, createAt, commentDto);
    }

    public static ArticleDetailDto from (Article entity) {
        return new ArticleDetailDto (
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getViewCount(),
                entity.getHashTag(),
                entity.getMember().getNickname(),
                entity.getCreateAt(),
                entity.getArticleComments().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}