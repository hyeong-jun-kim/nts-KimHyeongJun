package com.ntech.board.dto.comment;

import com.ntech.board.domain.Comment;
import com.ntech.board.domain.Post;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreateCommentReq {
    private long postId;
    private long parentCommentId; // 0이 아니면 대댓글
    private String content;
    private String writer;
    private String password;

    public static CreateCommentReq toDto(Comment comment){
        return CreateCommentReq.builder()
                .postId(comment.getPost().getId())
                .parentCommentId(comment.getParentComment().getId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .build();
    }

    public Comment toEntity(Post post){
        return Comment.builder()
                .post(post)
                .content(getContent())
                .writer(getWriter())
                .password(getPassword())
                .build();

    }
}
