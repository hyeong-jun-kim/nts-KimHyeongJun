package com.ntech.board.dto.comment;

import lombok.Getter;

@Getter
public class ValidateCommentReq {
    private long commentId;
    private String password;
}
