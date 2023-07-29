package com.ntech.board.domain;

import com.ntech.board.config.domain.BaseEntity;
import com.ntech.board.config.status.BaseStatus;
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

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childComment = new ArrayList<>();

    private String writer;

    private String content;

    private String password;

    @Override
    public void remove(){
        super.changeStatus(BaseStatus.INACTIVE);
        this.content = "삭제된 댓글입니다.";
    }

    public void changeContent(String content){
        this.content = content;
    }

    public void updateParentComment(Comment comment){
        this.parentComment = comment;
    }

    public void addChildComment(Comment comment){
        this.childComment.add(comment);
    }
}
