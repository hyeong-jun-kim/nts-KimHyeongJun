package com.ntech.board.dto.post;

import lombok.Getter;

@Getter
public class DeletePostReq {
    private long postId;
    private String password;
}
