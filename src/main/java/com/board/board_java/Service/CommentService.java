package com.board.board_java.Service;

import com.board.board_java.domain.type.SearchType;
import com.board.board_java.dto.ArticleDto;
import com.board.board_java.dto.CommentDto;
import com.board.board_java.repository.ArticleRepository;
import com.board.board_java.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class CommentService {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public List<CommentDto> searchComments(long articleId) {
        return List.of();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long id) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {

    }

    public void updateArticle(long id, ArticleDto dto) {

    }

    public void deleteArticle(long id) {

    }
}
