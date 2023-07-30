package com.ntech.board.repository.qeury;

import com.ntech.board.config.status.BaseStatus;
import com.ntech.board.domain.Comment;
import com.ntech.board.domain.Post;
import com.ntech.board.dto.comment.GetCommentListRes;
import com.ntech.board.dto.comment.GetCommentRes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.ntech.board.domain.QComment.comment;

@Repository
@RequiredArgsConstructor
public class CommentCustomRepository {
    private final JPAQueryFactory query;
    private final String DELETE_CONTENT = "삭제된 댓글입니다.";

    public GetCommentListRes getCommentsByPost(Post post, Pageable pageable) {
        List<Comment> commentList = query.select(comment)
                .from(comment)
                .where(comment.post.eq(post))
                .orderBy(comment.createdAt.desc(), comment.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1) // 페이징을 위해 11개 가져옴
                .fetch();

        boolean isFinalPage = true; // 마지막 페이지인가

        if (commentList.size() == pageable.getPageSize() + 1) { // 6이면 다음 페이지가 있음
            commentList.remove(pageable.getPageSize()); // 5개만 리턴해야 하니까 지워버림
            isFinalPage = false;
        }

        // 삭제된 댓글 -> '삭제된 댓글입니다.'로 내용 수정
        List<GetCommentRes> commentResList = commentList.stream()
                .map(comment -> {
                    GetCommentRes commentRes = GetCommentRes.toDto(comment);

                    if (comment.getStatus() == BaseStatus.INACTIVE)
                        commentRes.getComment().setContent(DELETE_CONTENT);
                    return commentRes;
                }).toList();

        return new GetCommentListRes(isFinalPage, commentResList);
    }
}
