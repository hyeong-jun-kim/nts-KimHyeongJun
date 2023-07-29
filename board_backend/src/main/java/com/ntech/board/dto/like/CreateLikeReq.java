package com.ntech.board.dto.like;

import lombok.Getter;

@Getter
public class CreateLikeReq {
    private long postId;
    private String likeType;
}
