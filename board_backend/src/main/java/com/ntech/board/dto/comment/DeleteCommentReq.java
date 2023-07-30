package com.ntech.board.dto.comment;

import lombok.Getter;

@Getter
public class DeleteCommentReq {
    private long commentId;
    private String password;
}
