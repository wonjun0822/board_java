package com.board.board_java.Service;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.Member;
import com.board.board_java.domain.type.SearchType;
import com.board.board_java.dto.Article.ArticleDetailDto;
import com.board.board_java.dto.Article.ArticleDto;
import com.board.board_java.dto.Article.ArticleWriteDto;
import com.board.board_java.dto.Security.LoginMemberDto;
import com.board.board_java.repository.ArticleRepository;

import com.board.board_java.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType searchType, String searchKeyword, Pageable pageable) {
        if (searchKeyword == null || searchKeyword.isBlank()) {
            return articleRepository.findAll(pageable).map(ArticleDto::from);
        }

        return switch (searchType) {
            case TITLE -> articleRepository.findByTitleContaining(searchKeyword, pageable).map(ArticleDto::from);
            case CONTENT -> articleRepository.findByContentContaining(searchKeyword, pageable).map(ArticleDto::from);
            case ID -> null;
            case NICKNAME -> null;
            case HASHTAG -> articleRepository.findByHashTag(searchKeyword, pageable).map(ArticleDto::from);
        };
    }

    @Transactional(readOnly = true)
    public ArticleDetailDto getArticle(long id) {
        return articleRepository.findById(id).map(ArticleDetailDto::from).orElseThrow(() -> new EntityNotFoundException("존재하는 게시글이 없습니다."));
    }

    public ArticleDetailDto saveArticle(ArticleWriteDto articleWriteDto, LoginMemberDto loginMemberDto) {
        Member member = memberRepository.getReferenceById(loginMemberDto.getUsername());

        Article article = articleWriteDto.toEntity(member);

        articleRepository.save(article);

        return ArticleDetailDto.from(article);
    }

    public ArticleDetailDto updateArticle(Long articleId, ArticleWriteDto articleWriteDto, LoginMemberDto loginMemberDto) {
        try {
            var article = articleRepository.getReferenceById(articleId);
            var member = memberRepository.getReferenceById(loginMemberDto.getUsername());

            if (article.getMember().equals(member)) {
                if (articleWriteDto.title() != null) {
                    article.setTitle(articleWriteDto.title());
                }

                if (articleWriteDto.content() != null) {
                    article.setContent(articleWriteDto.content());
                }

                articleRepository.save(article);

                return ArticleDetailDto.from(article);
            }

            else {
                return ArticleDetailDto.from(article);
            }
        } catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 수정하는데 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }

        return null;
    }

    public void deleteArticle(Long articleId, LoginMemberDto loginMemberDto) {
        try {
            var article = articleRepository.getReferenceById(articleId);
            var member = memberRepository.getReferenceById(loginMemberDto.getUsername());

            if (article.getMember().equals(member)) {
                articleRepository.deleteById(articleId);
            }
        }

        catch (EntityNotFoundException e) {
            log.warn("게시글 삭제 실패. 게시글을 삭제하는데 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
        }
    }
}
