package com.ntech.board.repository;

import com.ntech.board.domain.Comment;
import com.ntech.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 게시글 댓글 갯수 가져오기
    int countByPost(Post post);
}
