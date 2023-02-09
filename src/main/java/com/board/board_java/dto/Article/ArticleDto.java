package com.board.board_java.dto.Article;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.Member;
import com.board.board_java.dto.Member.MemberDto;

import java.time.LocalDateTime;

/**
 * A DTO for the {@link com.board.board_java.domain.Article} entity
 */
public record ArticleDto(
        Long id,
        String title,
        String content,
        int viewCount,
        String hashTag,
        String nickname,
        LocalDateTime createAt
) {
    public static ArticleDto of(Long id, String title, String content, int viewCount, String hashTag, String nickname, LocalDateTime createAt) {
        return new ArticleDto(id, title, content, viewCount, hashTag, nickname, createAt);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getViewCount(),
                entity.getHashTag(),
                entity.getMember().getNickname(),
                entity.getCreateAt()
        );
    }

    public Article toEntity(Member member) {
        return Article.of(
                title,
                content,
                viewCount,
                member
        );
    }
}