package com.board.board_java.dto;

import com.board.board_java.domain.Article;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A DTO for the {@link com.board.board_java.domain.Article} entity
 */
public record ArticleWithCommentsDto (
        Long id,
        String title,
        String content,
        int viewCount,
        String hashTag,
        Set<CommentDto> commentDto
) {
    public static ArticleWithCommentsDto of(Long id, String title, String content, int viewCount, String hashTag, Set<CommentDto> commentDto) {
        return new ArticleWithCommentsDto(id, title, content, viewCount, hashTag, commentDto);
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto (
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getViewCount(),
                entity.getHashTag(),
                entity.getArticleComments().stream()
                        .map(CommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
}