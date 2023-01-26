package com.board.board_java.repository;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.Comment;
import com.board.board_java.domain.QArticle;
import com.board.board_java.domain.QComment;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CommentRepository extends
        JpaRepository<Comment, Long>,
        QuerydslPredicateExecutor<Comment>,
        QuerydslBinderCustomizer<QComment>
{
    @Override
    default void customize(QuerydslBindings bindings, QComment root) {
        bindings.excludeUnlistedProperties(true);

        bindings.including(root.comment);

        //bindings.bind(root.comment).first(StringExpression::likeIgnoreCase); // like '%{value}'
        bindings.bind(root.comment).first(StringExpression::containsIgnoreCase); // like '%{value}%'

        //bindings.bind(root.createDate).first(DateTimeExpression::eq);
    }
}