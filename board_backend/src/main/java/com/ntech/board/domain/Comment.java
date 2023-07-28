package com.ntech.board.domain;

import com.ntech.board.config.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Builder
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment; // 대댓글 구분용 외래키

    @OneToMany(mappedBy = "parentComment", orphanRemoval = true)
    private List<Comment> childComment = new ArrayList<>();

    private String writer;

    private String content;

    private String password;

    public void addComment(Comment comment){
        this.childComment.add(comment);
    }

    public void updateParentComment(Comment comment){
        this.parentComment = comment;
    }
}