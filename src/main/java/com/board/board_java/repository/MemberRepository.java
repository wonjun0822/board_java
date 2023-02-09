package com.board.board_java.repository;

import com.board.board_java.domain.Article;
import com.board.board_java.domain.Member;
import com.board.board_java.domain.QComment;
import com.board.board_java.domain.QMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String>
{
}
