package com.ntech.board.repository;

import com.ntech.board.config.type.LikeType;
import com.ntech.board.domain.Like;
import com.ntech.board.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    // 좋아요, 싫어요 갯수 가져오기
    int countByPostAndLikeType(Post post, LikeType likeType);

    // 이미 좋아요를 누른 아이피인지 검사
    boolean existsByPostAndIp(Post post, String ip);
}
