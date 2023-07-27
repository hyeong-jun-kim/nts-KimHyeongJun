package com.ntech.board.dto.post;

import com.ntech.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreatePostReq {
    private String writer;
    private String password;

    private String content;
    private String title;

    public Post toEntity(String encryptPwd){
        return Post.builder()
                .writer(getWriter())
                .password(encryptPwd)
                .content(getContent())
                .title(getTitle())
                .build();
    }
}
