package com.ntech.board.service;

import com.ntech.board.domain.HashTag;
import com.ntech.board.domain.Post;
import com.ntech.board.domain.PostHashTag;
import com.ntech.board.repository.HashTagRepository;
import com.ntech.board.repository.PostHashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HashTagService {
    private final HashTagRepository hashTagRepository;
    private final PostHashTagRepository postHashTagRepository;

    // 해시태그 생성
    public void createHashTags(Post post, List<String> hashtags) {
        if (hashtags == null)
            return;

        hashtags.stream()
                .map(hashtag -> hashTagRepository.findByName(hashtag)
                        .orElseGet(() -> hashTagRepository.save(new HashTag(hashtag)))) // 해시태그 존재 안하면 새로 생성
                .forEach(hashtag -> postHashTagRepository.save(new PostHashTag(post, hashtag))); // 게시물 - 해시태그 매핑
    }

    // 해시태그 불러오기
    public List<String> getHashTags(Post post){
        List<PostHashTag> postHashTags = postHashTagRepository.findAllByPost(post);

        if(postHashTags == null)
            return null;

        return postHashTags.stream().map(pt -> pt.getHashTag().getName()).collect(Collectors.toList());
    }
}
