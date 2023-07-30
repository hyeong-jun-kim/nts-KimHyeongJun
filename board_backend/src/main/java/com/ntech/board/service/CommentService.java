package com.ntech.board.service;

import com.ntech.board.config.response.BaseException;
import com.ntech.board.domain.Comment;
import com.ntech.board.domain.Post;
import com.ntech.board.dto.comment.*;
import com.ntech.board.repository.CommentRepository;
import com.ntech.board.repository.PostRepository;
import com.ntech.board.repository.qeury.CommentCustomRepository;
import com.ntech.board.utils.encrypt.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ntech.board.config.page.PageResult.COMMENT_PAGE_SIZE;
import static com.ntech.board.config.response.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentCustomRepository commentCustomRepository;
    private final PostRepository postRepository;

    private final SHA256 sha256;

    // 댓글 생성
    public GetCommentListRes createComment(CreateCommentReq commentReq){
        long postId = commentReq.getPostId();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(NOT_EXIST_POST));

        commentReq.setPassword(sha256.encrypt(commentReq.getPassword())); // 비밀번호 암호화
        Comment comment = commentReq.toEntity(post);

        // 게시글 댓글 불러오기
        long parentCommentId = commentReq.getParentCommentId();
        if(parentCommentId != 0){
            Comment parentComment = commentRepository.findById(parentCommentId)
                            .orElseThrow(() -> new BaseException(NOT_EXIST_COMMENT));
            comment.updateParentComment(parentComment);
        }

        commentRepository.save(comment);

        return getFirstComments(post);
    }

    // 댓글 비밀번호 검증
    public void validatePassword(ValidateCommentReq validateCommentReq){
        Comment comment = commentRepository.findById(validateCommentReq.getCommentId())
                .orElseThrow(() -> new BaseException(NOT_EXIST_COMMENT));

        checkPassword(comment, validateCommentReq.getPassword());
    }

    // 댓글 수정하기
    public void modifyComment(ModifyCommentReq commentReq){
        Comment comment = commentRepository.findById(commentReq.getCommentId())
                .orElseThrow(() -> new BaseException(NOT_EXIST_COMMENT));

        checkPassword(comment, commentReq.getPassword()); // 비밀번호 다시한번 검증

        comment.changeContent(commentReq.getContent());
    }

    // 댓글 삭제하기
    public void deleteComment(DeleteCommentReq commentReq){
        Comment comment = commentRepository.findById(commentReq.getCommentId())
                .orElseThrow(() -> new BaseException(NOT_EXIST_COMMENT));

        checkPassword(comment, commentReq.getPassword()); // 비밀번호 다시한번 검증
        comment.remove();
    }

    // 댓글 더 불러오기
    public GetCommentListRes getComments(long postId, int page){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(NOT_EXIST_POST));

        PageRequest pageRequest = PageRequest.of(page, COMMENT_PAGE_SIZE, Sort.by("id").descending());
        return commentCustomRepository.getCommentsByPost(post, pageRequest);
    }


    /**
     * 편의 메서드
     */
    // 댓글 비밀번호 검증하기
    public void checkPassword(Comment comment, String inputPassword){
        // 비밀번호 검증
        String encryptPwd = sha256.encrypt(inputPassword);
        if(!comment.getPassword().equals(encryptPwd))
            throw new BaseException(NOT_MATCH_PASSWORD);
    }


    // 게시글 첫 댓글 불러오기
    public GetCommentListRes getFirstComments(Post post){
        PageRequest pageRequest = PageRequest.of(0, COMMENT_PAGE_SIZE, Sort.by("id").descending());
        return commentCustomRepository.getCommentsByPost(post, pageRequest);
    }
}
