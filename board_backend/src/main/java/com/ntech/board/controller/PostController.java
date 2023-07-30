package com.ntech.board.controller;

import com.ntech.board.config.page.PageResult;
import com.ntech.board.config.response.BaseResponse;
import com.ntech.board.config.response.BaseResponseStatus;
import com.ntech.board.dto.post.*;
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
    public BaseResponse<CreatePostRes> createPost(@RequestBody CreatePostReq postReq) {
        CreatePostRes response = postService.createPost(postReq);
        return new BaseResponse<>(response);
    }

    // 게시글 수정하기
    @PatchMapping("/modify/{postId}")
    public BaseResponse<String> modifyPost(@PathVariable("postId") long postId, @RequestBody CreatePostReq postReq) {
        BaseResponseStatus response = postService.modifyPost(postId, postReq);
        return new BaseResponse<>(response);
    }

    // 게시글 삭제하기
    @PostMapping("/delete")
    public BaseResponse<String> deletePost(@RequestBody DeletePostReq postReq){
        BaseResponseStatus response = postService.deletePost(postReq);
        return new BaseResponse<>(response);
    }

    // 게시글 목록 불러오기 (페이징)
    @GetMapping("/list")
    public BaseResponse<PageResult<GetPostRes>> getPostPagingList(@RequestParam("page") int page) {
        PageResult<GetPostRes> postPages = postService.getPostPagingList(page);
        return new BaseResponse<>(postPages);
    }

    // 게시글 상세보기 (댓글도 불러옴)
    @GetMapping("/{postId}")
    public BaseResponse<GetPostRes> getPost(@PathVariable("postId") long postId) {
        GetPostRes postPage = postService.getPost(postId);
        return new BaseResponse<>(postPage);
    }

    // 게시글 검색 : 제목, 작성자, 해시태그
    @GetMapping("/list/search")
    public BaseResponse<PageResult<GetPostRes>> getSearchPostPagingList(@RequestParam("type") String type, @RequestParam("name") String name
            , @RequestParam("page") int page) {
        PageResult<GetPostRes> postPages = postService.getSearchPostPagingList(page, type, name);
        return new BaseResponse<>(postPages);
    }

    // 게시글 비밀번호 검증
    @PostMapping("/validate")
    public BaseResponse<String> validatePostPassword(@RequestBody ValidatePostReq postReq){
        BaseResponseStatus response = postService.validatePostPassword(postReq);
        return new BaseResponse<>(response);
    }
}
