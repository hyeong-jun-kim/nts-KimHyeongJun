package com.ntech.board.dto.post;

import com.ntech.board.domain.Post;
import com.ntech.board.dto.comment.GetCommentRes;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetPostRes {
    private long postId;
    private String title;
    private String content;
    private int likeCnt;
    private int unLikeCnt;
    private int viewCount;
    private String writer;
    private String createdAt;
    private boolean isNew;

    private List<GetCommentRes> comments;
    private List<String> hashtags;

    public static GetPostRes toDto(Post post, int likeCnt, int unLikeCnt){
        return GetPostRes.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .viewCount(post.getViewCount())
                .likeCnt(likeCnt)
                .unLikeCnt(unLikeCnt)
                .writer(post.getWriter())
                .createdAt(post.getCreatedAt())
                .build();
    }
}
