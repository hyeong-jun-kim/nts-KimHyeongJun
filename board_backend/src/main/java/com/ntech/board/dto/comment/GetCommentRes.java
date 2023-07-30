package com.ntech.board.dto.comment;

import com.ntech.board.config.status.BaseStatus;
import com.ntech.board.domain.Comment;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GetCommentRes {
    private CommentDTO comment;

    private List<CommentDTO> childComments; // 대댓글

    public static GetCommentRes toDto(Comment comment) {
        return builder()
                .comment(CommentDTO.toDto(comment))
                // 대댓글 CommentDTO List로 변환
                .childComments(comment.getChildComment() == null ? null :
                        comment.getChildComment().stream()
                                .map(CommentDTO::toDto).collect(Collectors.toList()))
                .build();
    }
}
