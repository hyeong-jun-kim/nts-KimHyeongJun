package com.ntech.board.config.page;

import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
public class PageResult<T> {
    public static final int PAGE_SIZE = 10;

    private int totalPage;
    private int page;
    private int size;
    private int start, end;
    private boolean prev, next;
    private List<Integer> pageList;

    private List<T> contents;

    public PageResult(Page<T> page){
        this.contents = page.stream().collect(Collectors.toList());
        this.totalPage = page.getTotalPages();

        makePageList(page.getPageable());
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        int tmpEnd = (int)(Math.ceil(page/10.0)) * 10;
        this.start = tmpEnd - 9;
        this.end = Math.min(this.totalPage, tmpEnd);
        this.next = totalPage > tmpEnd;
        this.pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
