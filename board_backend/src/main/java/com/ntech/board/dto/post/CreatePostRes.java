package com.ntech.board.dto.post;

import com.ntech.board.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreatePostRes {
    private long id;

    public static CreatePostRes toDto(Post post) {
        return new CreatePostRes(post.getId());
    }
}
