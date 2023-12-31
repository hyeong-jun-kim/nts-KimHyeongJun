package com.ntech.board.domain;

import com.ntech.board.config.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long id;

    private String writer;

    private String password;

    private String content;

    private String title;

    private int viewCount;

    @OneToMany(mappedBy = "post", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostHashTag> postHashTags = new ArrayList<>();

    public void addLike(Like like){
        this.likes.add(like);
    }
    public void increaseViewCount(){
        this.viewCount++;
    }
    public void modifyPost(String title, String content){
        if(!this.title.equals(title))
            this.title = title;

        if(!this.content.equals(content))
            this.content = content;
    }
}
