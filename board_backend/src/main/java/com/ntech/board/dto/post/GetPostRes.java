package com.ntech.board.dto.post;

import com.ntech.board.domain.Post;
import com.ntech.board.dto.comment.GetCommentListRes;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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

    private GetCommentListRes commentPage;
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
