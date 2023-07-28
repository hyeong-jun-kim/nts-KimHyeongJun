package com.ntech.board.controller;

import com.ntech.board.config.response.BaseResponse;
import com.ntech.board.dto.comment.CreateCommentReq;
import com.ntech.board.dto.comment.GetCommentRes;
import com.ntech.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/create")
    public BaseResponse<List<GetCommentRes>> createComment(@RequestBody CreateCommentReq commentReq){
        List<GetCommentRes> commentResList = commentService.createComment(commentReq);
        return new BaseResponse<>(commentResList);
    }
 }