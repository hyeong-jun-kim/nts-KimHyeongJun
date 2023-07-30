package com.ntech.board.dto.comment;

import com.ntech.board.domain.Comment;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetCommentListRes {
    private boolean isFinal; // 마지막 페이지면 true, 마지막 페이지가 아니면 false

    private List<GetCommentRes> comments;
}
