package com.ntech.board.dto.post;

import com.ntech.board.domain.Post;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetPostRes {
    private String title;
    private String content;
    private int likeCnt;
    private int unLikeCnt;

    public static GetPostRes toDto(Post post, int likeCnt){
        return GetPostRes.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .likeCnt(likeCnt)
                .build();
    }
}
