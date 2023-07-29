package com.ntech.board.dto.post;

import lombok.Getter;

@Getter
public class ValidatePostReq {
    private long postId;
    private String password;
}
