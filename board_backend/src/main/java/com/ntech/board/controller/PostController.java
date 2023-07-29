package com.ntech.board.controller;

import com.ntech.board.config.page.PageResult;
import com.ntech.board.config.response.BaseResponse;
import com.ntech.board.dto.post.CreatePostReq;
import com.ntech.board.dto.post.CreatePostRes;
import com.ntech.board.dto.post.GetPostRes;
import com.ntech.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 생성
    @PostMapping("/create")
    public BaseResponse<CreatePostRes> createPost(@RequestBody CreatePostReq dto){
        CreatePostRes response = postService.createPost(dto);
        return new BaseResponse<>(response);
    }

    // 게시글 목록 불러오기 (페이징)
    @GetMapping("/list")
    public BaseResponse<PageResult<GetPostRes>> getPostPagingList(@RequestParam("page") int page){
        PageResult<GetPostRes> postPages = postService.getPostPagingList(page);
        return new BaseResponse<>(postPages);
    }

    // 게시글 상세보기 (댓글도 불러옴)
    @GetMapping("/{postId}")
    public BaseResponse<GetPostRes> getPost(@PathVariable("postId") long postId){
        GetPostRes postPage = postService.getPost(postId);
        return new BaseResponse<>(postPage);
    }
}
