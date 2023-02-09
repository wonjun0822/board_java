package com.board.board_java.dto.Article;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.Member;
import com.board.board_java.dto.Member.MemberDto;

public record ArticleWriteDto (
    String title,
    String content
) {
    public static ArticleWriteDto of(String title, String content) {
        return new ArticleWriteDto(title, content);
    }

    public Article toEntity(Member member) {
        return Article.of(
                title,
                content,
                0,
                member
        );
    }
}
