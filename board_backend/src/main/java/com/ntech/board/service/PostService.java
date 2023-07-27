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

import static com.ntech.board.config.response.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final SHA256 sha256;

    // 게시글 생성하기
    public CreatePostRes createBoard(CreatePostReq boardReqDto){
        validateCreateBoard(boardReqDto); // 게시글 생성 검증

        String encryptPassword = sha256.encrypt(boardReqDto.getPassword());
        Post post = boardReqDto.toEntity(encryptPassword);

        postRepository.save(post);
        return CreatePostRes.toDto(post);
    }

    // 게시글 검증 함수
    public void validateCreateBoard(CreatePostReq boardReqDto){
        // 검증 : 4 <= pwd.length <= 15
        if(boardReqDto.getPassword().length() < 4 || boardReqDto.getPassword().length() > 15)
            throw new BaseException(PASSWORD_LENGTH_ERROR);

        // 검증 : 1 <= writer.length <= 10
        if(boardReqDto.getWriter().length() < 1 || boardReqDto.getWriter().length() > 10)
            throw new BaseException(WRITER_LENGTH_ERROR);

        // 검증 : 1 <= title.length <= 30
        if(boardReqDto.getTitle().length() < 1 || boardReqDto.getTitle().length() > 30)
            throw new BaseException(TITLE_LENGTH_ERROR);

        // 검증 : 1 <= content.length <= 500
        if(boardReqDto.getContent().length() < 1 || boardReqDto.getContent().length() > 500)
            throw new BaseException(PASSWORD_LENGTH_ERROR);
    }
}
