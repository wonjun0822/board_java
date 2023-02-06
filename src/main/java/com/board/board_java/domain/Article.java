package com.board.board_java.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
//@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashTag"),
        @Index(columnList = "createBy"),
        @Index(columnList = "createAt")
})
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Setter @Column(nullable = false, length = 100) private String title;
    @Setter @Column(nullable = false, length = 500) private String content;
    @Setter @Column(nullable = false, columnDefinition = "int default 0") private int viewCount;
    @Setter @Column(length = 100) private String hashTag;

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

    @ToString.Exclude
    @OrderBy("id desc")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<Comment> articleComments = new LinkedHashSet<>();

    @Setter
    @JoinColumn(name = "createBy", referencedColumnName = "memberId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    @ManyToOne(optional = false)
    private Member member;

    protected Article() {}

    private Article(String title, String content, int viewCount, String hashTag) {
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.hashTag = hashTag;
    }

    public static Article of(String title, String content, int viewCount, String hashTag) {
        return new Article(title, content, viewCount, hashTag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id == article.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
