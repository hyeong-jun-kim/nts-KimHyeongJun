package com.ntech.board.repository;

import com.ntech.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
    // 페이징으로 게시글 10개씩 조회하기
    @Query(value = "select p from Post p where p.status = 'ACTIVE'",
    countQuery = "select count(p) from Post p where p.status = 'ACTIVE'") // 불 필요한 조인을 막기위한 카운트 쿼리 분리
    Page<Post> findPostPage(Pageable pageable);
}
