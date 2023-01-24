package com.board.board_java.domain;

import java.time.LocalDateTime;

public class Comment {
    private long CommentId;
    private Article article;
    private String comment;

    private String createId;
    private LocalDateTime createDate;
    private String updateId;
    private LocalDateTime updateDate;
}
