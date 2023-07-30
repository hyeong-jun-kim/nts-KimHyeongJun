package com.ntech.board.config.page;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PageResult<T> {
    public static final int PAGE_SIZE = 10;

    private int totalPage;
    private int page;
    private int size;
    private List<T> contents;

    public PageResult(Page<T> page){
        this.totalPage = page.getTotalPages();
        this.page = page.getPageable().getPageNumber() + 1;
        this.size = page.getPageable().getPageSize();
        this.contents = page.stream().collect(Collectors.toList());
    }
}
