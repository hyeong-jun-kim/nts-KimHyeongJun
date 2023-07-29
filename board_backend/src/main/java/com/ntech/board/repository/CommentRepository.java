package com.ntech.board.repository;

import com.ntech.board.domain.Comment;
import com.ntech.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c left join fetch c.childComment " +
            "where c.post = :post and c.parentComment is null and c.status = 'ACTIVE'")
    List<Comment> getCommentsByPost(@Param("post") Post post); // 댓글 불러오기
}
