package com.ntech.board.controller;

import com.ntech.board.config.response.BaseResponse;
import com.ntech.board.dto.post.CreatePostReq;
import com.ntech.board.dto.post.CreatePostRes;
import com.ntech.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 게시글 생성
    @PostMapping("/create")
    public BaseResponse<CreatePostRes> createPost(CreatePostReq dto){
        CreatePostRes response = postService.createBoard(dto);
        return new BaseResponse<>(response);
    }
}
