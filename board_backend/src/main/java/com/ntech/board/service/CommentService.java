package com.ntech.board.service;

import com.ntech.board.config.response.BaseException;
import com.ntech.board.config.response.BaseResponseStatus;
import com.ntech.board.domain.Comment;
import com.ntech.board.domain.Post;
import com.ntech.board.dto.comment.CommentDTO;
import com.ntech.board.dto.comment.CreateCommentReq;
import com.ntech.board.dto.comment.GetCommentRes;
import com.ntech.board.repository.CommentRepository;
import com.ntech.board.repository.CommentRepositoryCustom;
import com.ntech.board.repository.PostRepository;
import com.ntech.board.utils.encrypt.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.ntech.board.config.response.BaseResponseStatus.NOT_EXIST_COMMENT;
import static com.ntech.board.config.response.BaseResponseStatus.NOT_EXIST_POST;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentRepositoryCustom commentRepositoryCustom;
    private final PostRepository postRepository;


    private final SHA256 sha256;

    // 댓글 생성
    public List<GetCommentRes> createComment(CreateCommentReq commentReq){
        long postId = commentReq.getPostId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(NOT_EXIST_POST));

        // TODO 댓글 생성 검증

        commentReq.setPassword(sha256.encrypt(commentReq.getPassword())); // 비밀번호 암호화
        Comment comment = commentReq.toEntity(post);

        // 대 댓글일 경우 parentComment 추가
        long parentCommentId = commentReq.getParentCommentId();
        if(parentCommentId != 0){
            Comment parentComment = commentRepository.findById(parentCommentId)
                            .orElseThrow(() -> new BaseException(NOT_EXIST_COMMENT));
            comment.updateParentComment(parentComment);
        }

        commentRepository.save(comment);

        return getCommentList(post);
    }

    // 댓글 불러오기
    public List<GetCommentRes> getCommentList(Post post){
        List<Comment> parentComment = commentRepositoryCustom.findParentComments(post); // 대댓글 제외한 부모 comment 리스트 받아옴
        return parentComment.stream().map(GetCommentRes::toDto).collect(Collectors.toList());
    }
}