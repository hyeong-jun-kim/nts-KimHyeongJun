package com.ntech.board.domain;

import com.ntech.board.config.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * HashTag - Post 조인 테이블
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
public class PostHashTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_hashtag_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private HashTag hashTag;

    public PostHashTag(Post post, HashTag hashTag){
        this.post = post;
        this.hashTag = hashTag;
    }
}
