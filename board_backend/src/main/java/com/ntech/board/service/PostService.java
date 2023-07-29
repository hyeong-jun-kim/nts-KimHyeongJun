package com.ntech.board.service;

import com.ntech.board.config.page.PageResult;
import com.ntech.board.config.response.BaseException;
import com.ntech.board.config.type.LikeType;
import com.ntech.board.domain.Post;
import com.ntech.board.dto.comment.GetCommentRes;
import com.ntech.board.dto.post.CreatePostReq;
import com.ntech.board.dto.post.CreatePostRes;
import com.ntech.board.dto.post.GetPostRes;
import com.ntech.board.repository.LikeRepository;
import com.ntech.board.repository.PostRepository;
import com.ntech.board.utils.encrypt.SHA256;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static com.ntech.board.config.page.PageResult.PAGE_SIZE;
import static com.ntech.board.config.response.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final CommentService commentService;

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;

    private final SHA256 sha256;

    // 게시글 생성하기
    public CreatePostRes createPost(CreatePostReq postReqDto) {
        validateCreateBoard(postReqDto); // 게시글 생성 검증

        String encryptPassword = sha256.encrypt(postReqDto.getPassword());
        Post post = postReqDto.toEntity(encryptPassword);

        postRepository.save(post);
        return CreatePostRes.toDto(post);
    }

    // 게시글 목록 불러오기 (페이징 처리)
    public PageResult<GetPostRes> getPostPagingList(int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, PAGE_SIZE, Sort.by("id").descending());

        Page<GetPostRes> postPage = postRepository.findPostPage(pageRequest).map(p -> {
            int likeCnt = likeRepository.countByPostAndLikeType(p, LikeType.LIKE); // 게시글 좋아요 수 카운트

            GetPostRes getPostRes = GetPostRes.toDto(p, likeCnt, 0);

            // 3일이내 등록된 게시글인지 확인
            if(isWithin3DaysFromPost(p.getCreatedAt()))
                getPostRes.setNew(true);

            return getPostRes;
        });

        return new PageResult<>(postPage);
    }

    // 게시글 상세 보기
    public GetPostRes getPost(long postId){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BaseException(NOT_EXIST_POST));

        int likeCnt = likeRepository.countByPostAndLikeType(post, LikeType.LIKE);
        int unLikeCnt = likeRepository.countByPostAndLikeType(post, LikeType.UNLIKE);

        GetPostRes postRes = GetPostRes.toDto(post, likeCnt, unLikeCnt);

        List<GetCommentRes> comments = commentService.getComments(post);
        postRes.setComments(comments);

        return postRes;
    }

    // 게시글 검증 함수
    public void validateCreateBoard(CreatePostReq postReqDto) {
        // 검증 : 4 <= pwd.length <= 15
        if (postReqDto.getPassword().length() < 4 || postReqDto.getPassword().length() > 15)
            throw new BaseException(PASSWORD_LENGTH_ERROR);

        // 검증 : 1 <= writer.length <= 10
        if (postReqDto.getWriter().length() < 1 || postReqDto.getWriter().length() > 10)
            throw new BaseException(WRITER_LENGTH_ERROR);

        // 검증 : 1 <= title.length <= 30
        if (postReqDto.getTitle().length() < 1 || postReqDto.getTitle().length() > 30)
            throw new BaseException(TITLE_LENGTH_ERROR);

        // 검증 : 1 <= content.length <= 500
        if (postReqDto.getContent().length() < 1 || postReqDto.getContent().length() > 500)
            throw new BaseException(PASSWORD_LENGTH_ERROR);
    }

    // 3일이내인지 확인해주는 함수
    public boolean isWithin3DaysFromPost(String postCreatedAt){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd hh:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(postCreatedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        ZoneId zoneId = ZoneId.systemDefault(); // 기본 타임존 사용
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);

        long postTimeMills = zonedDateTime.toInstant().toEpochMilli();
        long currentTimeMills = System.currentTimeMillis();
        long differenceMills = currentTimeMills - postTimeMills;

        // 차이를 일(day)로 변환
        int differenceDays = (int) (differenceMills / (1000 * 60 * 60 * 24));

        // 3일 이내인지 확인
        return differenceDays <= 3;
    }
}
