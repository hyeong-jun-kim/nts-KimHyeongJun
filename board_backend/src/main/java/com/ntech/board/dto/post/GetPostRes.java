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
    private int viewCount;
    private String writer;
    private String createdAt;

    public static GetPostRes toDto(Post post, int likeCnt){
        return GetPostRes.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .viewCount(post.getViewCount())
                .likeCnt(likeCnt)
                .writer(post.getWriter())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
