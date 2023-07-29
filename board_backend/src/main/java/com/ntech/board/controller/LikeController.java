package com.ntech.board.controller;

import com.ntech.board.config.response.BaseResponse;
import com.ntech.board.config.response.BaseResponseStatus;
import com.ntech.board.dto.like.CreateLikeReq;
import com.ntech.board.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    // 좋아요, 싫어요 생성
    @PostMapping("/create")
    public BaseResponse<String> createLikeOrDislike(@RequestBody CreateLikeReq likeReq, HttpServletRequest servletRequest){
        BaseResponseStatus response = likeService.createLikeOrDisLike(likeReq, servletRequest);
        return new BaseResponse<>(response);
    }
}
