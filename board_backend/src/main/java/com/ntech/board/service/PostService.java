package com.ntech.board.service;

import com.ntech.board.config.response.BaseException;
import com.ntech.board.domain.Post;
import com.ntech.board.dto.post.CreatePostReq;
import com.ntech.board.dto.post.CreatePostRes;
import com.ntech.board.repository.PostRepository;
import com.ntech.board.utils.encrypt.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ntech.board.config.response.BaseResponseStatus.PASSWORD_LENGTH_ERROR;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final SHA256 sha256;

    // 게시글 생성하기
    public CreatePostRes createBoard(CreatePostReq boardReqDto){
        // 검증 : 비밀번호 길이가 0인경우
        if(boardReqDto.getPassword().length() == 0)
            throw new BaseException(PASSWORD_LENGTH_ERROR);

        String encryptPassword = sha256.encrypt(boardReqDto.getPassword());
        Post post = boardReqDto.toEntity(encryptPassword);

        postRepository.save(post);
        return CreatePostRes.toDto(post);
    }
}
