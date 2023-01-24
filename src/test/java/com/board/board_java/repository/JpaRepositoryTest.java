package com.board.board_java.repository;

import com.board.board_java.config.JpaConfig;
import com.board.board_java.domain.Article;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired CommentRepository commentRepository
    ) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
    }

    @DisplayName("Select 테스트")
    @Test
    void givenTEstData_whenSelecting_thenWorksFine() {
        List<Article> articleList = articleRepository.findAll();

        assertThat(articleList)
                .isNotNull()
                .hasSize(1000);
    }

    @DisplayName("Insert 테스트")
    @Test
    void givenTEstData_whenInserting_thenWorksFine() {
        long prevCount = articleRepository.count();

        Article article = articleRepository.save(Article.of("테스트", "테스트", 0, "#spring"));

        assertThat(articleRepository.count()).isEqualTo((prevCount + 1));
    }

    @DisplayName("Update 테스트")
    @Test
    void givenTEstData_whenUpdating_thenWorksFine() {
        Article prevArticle = articleRepository.findById(1L).orElseThrow();

        prevArticle.setHashTag("#springboot");

        Article savedArticle = articleRepository.saveAndFlush(prevArticle);

        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashTag", "#springboot");
    }

    @DisplayName("Delete 테스트")
    @Test
    void givenTEstData_whenDeleting_thenWorksFine() {
        Article article = articleRepository.findById(1L).orElseThrow();

        long prevCount = articleRepository.count();

        articleRepository.delete(article);

        assertThat(articleRepository.count()).isEqualTo((prevCount - 1));
    }
}