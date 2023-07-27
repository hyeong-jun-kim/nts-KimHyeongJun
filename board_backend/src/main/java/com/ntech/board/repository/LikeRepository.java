package com.ntech.board.repository;

import com.ntech.board.config.type.LikeType;
import com.ntech.board.domain.Like;
import com.ntech.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // 좋아요, 싫어요 갯수 가져오기
    int countByPostAndLikeType(Post post, LikeType likeType);
}
