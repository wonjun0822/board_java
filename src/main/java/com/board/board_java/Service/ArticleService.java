package com.board.board_java.Service;

import com.board.board_java.domain.type.SearchType;
import com.board.board_java.dto.ArticleDto;
import com.board.board_java.dto.ArticleWithCommentsDto;
import com.board.board_java.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
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
    public ArticleWithCommentsDto getArticle(long id) {
        return articleRepository.findById(id).map(ArticleWithCommentsDto::from).orElseThrow(() -> new EntityNotFoundException("존재하는 게시글이 없습니다."));
    }

    public void saveArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
    }

    public void updateArticle(ArticleDto dto) {
        try {
            var article = articleRepository.getReferenceById(dto.id());

            if (dto.title() != null) {
                article.setTitle(dto.title());
            }

            if (dto.content() != null) {
                article.setContent(dto.content());
            }

            article.setHashTag(dto.hashTag());

            articleRepository.save(article);
        }

        catch (EntityNotFoundException e) {
            log.warn("게시글 업데이트 실패. 게시글을 찾을 수 없습니다.");
        }
    }

    public void deleteArticle(long id) {
        articleRepository.deleteById(id);
    }
}
