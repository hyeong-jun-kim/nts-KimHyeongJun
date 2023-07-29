package com.ntech.board.domain;

import com.ntech.board.config.domain.BaseEntity;
import com.ntech.board.config.type.LikeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "likes")
public class Like extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String ip; // 중복 좋아요 방지용, IPv6 저장

    @Enumerated(EnumType.STRING)
    private LikeType likeType;

    public Like(Post post, String ip, LikeType likeType){
        this.post = post;
        this.ip = ip;
        this.likeType = likeType;
    }
}
