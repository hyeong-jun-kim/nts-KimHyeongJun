package com.ntech.board.dto.comment;

import com.ntech.board.domain.Comment;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CommentDTO {
    private long commentId;
    private String content;
    private String writer;
    private String createdAt;

    public static CommentDTO toDto(Comment comment){
        return CommentDTO.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(comment.getWriter())
                .createdAt(comment.getCreatedAt())
                .build();
    }
}
