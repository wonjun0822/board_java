package com.board.board_java.controller;

import com.board.board_java.Service.ArticleService;
import com.board.board_java.Service.PaginationService;
import com.board.board_java.domain.type.SearchType;
import com.board.board_java.dto.ArticleDto;
import com.board.board_java.dto.ArticleWithCommentsDto;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
//@RequestMapping("/articles")
//@Controller
@RequestMapping("/api/articles")
@RestController
public class ArticleController {
    private final ArticleService articleService;
    private final PaginationService paginationService;

    @GetMapping
    public Page<ArticleDto> articles(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchValue,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            ModelMap map
    ) {
        Page<ArticleDto> articles = articleService.searchArticles(searchType, searchValue, pageable);

        return articles;

        // List<Integer> barNumbers = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), articles.getTotalPages());

        // map.addAttribute("paginationBarNumbers", barNumbers);
        // map.addAttribute("articles", articles);
        // map.addAttribute("searchTypes", SearchType.values());
        // map.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

        // return "articles/index";
    }

    @GetMapping("/{id}")
    public ArticleWithCommentsDto article(@PathVariable Long id, ModelMap map) {
        var article = articleService.getArticle(id);

        return article;
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {

        articleService.deleteArticle(id);
    }
}
