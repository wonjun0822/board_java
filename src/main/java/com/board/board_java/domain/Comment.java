package com.board.board_java.domain;

import com.querydsl.core.types.EntityPath;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "comment"),
        @Index(columnList = "createBy"),
        @Index(columnList = "createAt")
})
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter @ManyToOne(optional = false) private Article article;
    @Setter @Column(nullable = false, length = 200) private String comment;

    @Setter
    @JoinColumn(name = "createBy", referencedColumnName = "memberId", nullable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Member member;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    @LastModifiedBy
    @Column(nullable = false, length = 50)
    private String updateBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;

    protected Comment() {}

    private Comment(Article article, String comment) {
        this.article = article;
        this.comment = comment;
    }

    public static Comment of(Article article, String comment) {
        return new Comment(article, comment);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id == comment.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
