package com.ntech.board.repository;

import com.ntech.board.domain.HashTag;
import com.ntech.board.domain.Post;
import com.ntech.board.domain.PostHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {
    @Query("select pt from PostHashTag pt join fetch pt.hashTag where pt.post = :post")
    List<PostHashTag> findAllByPost(@Param("post") Post post);
}
