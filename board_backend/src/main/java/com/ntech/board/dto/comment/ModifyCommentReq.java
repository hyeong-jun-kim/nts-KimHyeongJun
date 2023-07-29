package com.ntech.board.dto.comment;

import lombok.*;

@Getter
public class ModifyCommentReq {
    private long commentId;
    private String password;
    private String content;
}
