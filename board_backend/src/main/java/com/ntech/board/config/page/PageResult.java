package com.ntech.board.config.page;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PageResult<T> {
    public static final int POST_PAGE_SIZE = 10;
    public static final int COMMENT_PAGE_SIZE = 5;
    private long postCount; // 총 게시글 수
    private long commentCount; // 총 댓글 수

    private int totalPage;
    private int page;
    private int size;
    private List<T> contents;

    public PageResult(Page<T> page, long commentCount){
        this.totalPage = page.getTotalPages();
        this.page = page.getPageable().getPageNumber() + 1;
        this.size = page.getPageable().getPageSize();
        this.postCount = page.getTotalElements();
        this.contents = page.stream().collect(Collectors.toList());
        this.commentCount = commentCount;
    }
}
