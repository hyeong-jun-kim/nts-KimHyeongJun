package com.ntech.board.dto.comment;

import com.ntech.board.domain.Comment;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetCommentListRes {
    private List<GetCommentRes> comments;
}
