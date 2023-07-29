package com.ntech.board.repository;

import com.ntech.board.domain.HashTag;
import com.ntech.board.domain.PostHashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHashTagRepository extends JpaRepository<PostHashTag, Long> {
}
