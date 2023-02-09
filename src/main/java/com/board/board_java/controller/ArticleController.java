package com.board.board_java.controller;

import com.board.board_java.Service.ArticleService;
import com.board.board_java.domain.Article;
import com.board.board_java.domain.type.SearchType;
import com.board.board_java.dto.Article.ArticleDetailDto;
import com.board.board_java.dto.Article.ArticleDto;
import com.board.board_java.dto.Article.ArticleWriteDto;
import com.board.board_java.dto.Security.LoginMemberDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleService articleService;

    @Operation(summary="게시글 목록 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "게시글 목록 조회 성공"),
            @ApiResponse(responseCode = "404", description = "존재하지 않는 리소스 접근", content = @Content)
    })
    @GetMapping(produces = "application/json")
    public Page<ArticleDto> articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @ParameterObject @PageableDefault(page = 1, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<ArticleDto> articles = articleService.searchArticles(searchType, searchValue, pageable);

        return articles;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ArticleDetailDto article(@PathVariable Long id) {
        var article = articleService.getArticle(id);

        return article;
    }

    @PostMapping(produces = "application/json")
    public ArticleDetailDto saveArticle(@AuthenticationPrincipal LoginMemberDto loginMemberDto, ArticleWriteDto articleWriteDto) {
        return articleService.saveArticle(articleWriteDto, loginMemberDto);
    }

    @PutMapping(value = "/{articleId}", produces = "application/json")
    public ArticleDetailDto updateArticle(@PathVariable Long articleId, @AuthenticationPrincipal LoginMemberDto loginMemberDto, ArticleWriteDto articleWriteDto
    ) {
        return articleService.updateArticle(articleId, articleWriteDto, loginMemberDto);
    }

    @DeleteMapping("/{articleId}")
    public void deleteArticle(@PathVariable Long articleId, @AuthenticationPrincipal LoginMemberDto loginMemberDto) {

        articleService.deleteArticle(articleId, loginMemberDto);
    }
}
