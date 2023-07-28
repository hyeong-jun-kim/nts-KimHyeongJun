package com.ntech.board.repository;

import com.ntech.board.domain.Comment;
import com.ntech.board.domain.Post;
import com.ntech.board.dto.comment.GetCommentRes;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ntech.board.domain.QComment.comment;
import static com.ntech.board.domain.QPost.post;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    // 댓글 (대댓글 X) 조회하기
    public List<Comment> findParentComments(Post p){
        return queryFactory
                .select(comment)
                .from(comment)
                .join(comment.post, post)
                .where(comment.post.eq(p).and(comment.parentComment.isNull()))
                .fetch();
    }
}
