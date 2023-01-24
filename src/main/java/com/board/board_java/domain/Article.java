package com.board.board_java.domain;

import java.time.LocalDateTime;

public class Article {
    private long ArticleId;
    private String title;
    private String content;
    private int viewCount;
    private String hashTag;

    private String createId;
    private LocalDateTime createDate;
    private String updateId;
    private LocalDateTime updateDate;
}
