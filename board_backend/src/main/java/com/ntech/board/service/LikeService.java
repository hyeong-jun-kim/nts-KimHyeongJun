package com.ntech.board.service;

import com.ntech.board.config.response.BaseException;
import com.ntech.board.config.response.BaseResponseStatus;
import com.ntech.board.config.type.LikeType;
import com.ntech.board.domain.Like;
import com.ntech.board.domain.Post;
import com.ntech.board.dto.like.CreateLikeReq;
import com.ntech.board.repository.LikeRepository;
import com.ntech.board.repository.PostRepository;
import com.ntech.board.utils.ip.IPUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ntech.board.config.response.BaseResponseStatus.*;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;


    // 좋아요, 싫어요 생성
    public BaseResponseStatus createLikeOrDisLike(CreateLikeReq likeReq, HttpServletRequest servletRequest){
        String clientIP = IPUtils.getClientIpAddress(servletRequest);

        Post post = postRepository.findById(likeReq.getPostId())
                .orElseThrow(() -> new BaseException(NOT_EXIST_POST));

        // 이미 좋아요 누른 아이피인지 검사
        boolean checkExistIP = likeRepository.existsByPostAndIp(post, clientIP);
        if(checkExistIP)
            throw new BaseException(EXIST_LIKE_IP);

        LikeType likeType = LikeType.valueOf(likeReq.getLikeType());
        Like like = new Like(post, clientIP, likeType);
        likeRepository.save(like);

        if(likeType == LikeType.LIKE){
            return CREATE_LIKE_SUCCESS;
        }else{
            return CREATE_DISLIKE_SUCCESS;
        }
    }
}
