package com.board.board_java.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "comment"),
        @Index(columnList = "createId"),
        @Index(columnList = "createDate")
})
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter @ManyToOne(optional = false) private Article article;
    @Setter @Column(nullable = false, length = 200) private String comment;

    @CreatedBy @Column(nullable = false, length = 50) private String createId;
    @CreatedDate @Column(nullable = false) private LocalDateTime createDate;
    @LastModifiedBy @Column(nullable = false, length = 50) private String updateId;
    @LastModifiedDate @Column(nullable = false) private LocalDateTime updateDate;

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
