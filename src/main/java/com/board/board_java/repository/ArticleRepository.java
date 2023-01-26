package com.board.board_java.repository;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);

        bindings.including(root.title, root.content, root.hashTag);

        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase); // like '%{value}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%{value}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%{value}%'
        bindings.bind(root.hashTag).first(StringExpression::containsIgnoreCase); // like '%{value}%'

        //bindings.bind(root.createDate).first(DateTimeExpression::eq);
    }
}