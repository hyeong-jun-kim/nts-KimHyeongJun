package com.ntech.board.controller;

import com.ntech.board.config.response.BaseResponse;
import com.ntech.board.config.response.BaseResponseStatus;
import com.ntech.board.dto.comment.*;
import com.ntech.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.ntech.board.config.response.BaseResponseStatus.*;


@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/create")
    public BaseResponse<GetCommentListRes> createComment(@RequestBody CreateCommentReq commentReq){
        GetCommentListRes commentResList = commentService.createComment(commentReq);
        return new BaseResponse<>(commentResList);
    }

    // 댓글 비밀번호 검증
    @PostMapping("/validate")
    public BaseResponse<String> validateComment(@RequestBody ValidateCommentReq commentReq){
        commentService.validatePassword(commentReq);
        return new BaseResponse<>(USER_VALIDATE_SUCCESS);
    }

    // 댓글 수정
    @PatchMapping("/modify")
    public BaseResponse<String> modifyComment(@RequestBody ModifyCommentReq commentReq){
        commentService.modifyComment(commentReq);
        return new BaseResponse<>(COMMENT_MODIFY_SUCCESS);
    }

    // 댓글 삭제
    @PostMapping("/delete")
    public BaseResponse<String> deleteComment(@RequestBody DeleteCommentReq commentReq){
        commentService.deleteComment(commentReq);
        return new BaseResponse<>(COMMENT_DELETE_SUCCESS);
    }
 }
