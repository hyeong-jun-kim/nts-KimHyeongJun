package com.ntech.board.repository;

import com.ntech.board.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 페이징으로 게시글 10개씩 조회하기
    @Query(value = "select p from Post p where p.status = 'ACTIVE'",
    countQuery = "select count(p) from Post p where p.status = 'ACTIVE'") // 불 필요한 조인을 막기위한 카운트 쿼리 분리
    Page<Post> findPostPage(Pageable pageable);

    // 게시글 제목으로 검색
    @Query(value = "select p from Post p where p.status = 'ACTIVE' and p.title like %:title%",
            countQuery = "select count(p) from Post p where p.status = 'ACTIVE' and p.title like %:title%")
    Page<Post> findPostPageByTitle(Pageable pageable, @Param("title") String title);

    // 게시글 작성자로 검색
    @Query(value = "select p from Post p where p.status = 'ACTIVE' and p.writer like :writer",
            countQuery = "select count(p) from Post p where p.status = 'ACTIVE' and p.writer like :writer")
    Page<Post> findPostPageByWriter(Pageable pageable,@Param("writer") String writer);

    // 게시글 해시태그로 검색
    @Query(value = "select p from Post p join p.postHashTags pt where pt.status = 'ACTIVE' and pt.hashTag.name = :hashtag",
            countQuery = "select count(p) from Post p join p.postHashTags pt where pt.status = 'ACTIVE' and pt.hashTag.name = :hashtag")
    Page<Post> findPostPageByHashtag(Pageable pageable, @Param("hashtag")String hashtag);
}
