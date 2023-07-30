package com.ntech.board.service;

import com.ntech.board.config.domain.BaseEntity;
import com.ntech.board.domain.HashTag;
import com.ntech.board.domain.Post;
import com.ntech.board.domain.PostHashTag;
import com.ntech.board.repository.HashTagRepository;
import com.ntech.board.repository.PostHashTagRepository;
import lombok.RequiredArgsConstructor;
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
    public List<String> getHashTags(Post post) {
        List<PostHashTag> postHashTags = postHashTagRepository.findAllByPost(post);

        if (postHashTags == null)
            return null;

        return postHashTags.stream().map(pt -> pt.getHashTag().getName()).collect(Collectors.toList());
    }

    // 게시물 해시태그 수정하기
    // 인풋값에 있는데 기존 해시태그에는 없으면 => 새로 생성
    // 인풋값엔 없는데 기존 해시태그에는 있으면=> 삭제
    public void modifyPostHashTags(List<String> modifyHashtags, Post post) {
        List<PostHashTag> postHashTags = postHashTagRepository.findAllByPost(post);

        // 기존에 저장되어 있는 해시태그
        List<String> hashtags = postHashTags.stream().map(postHashTag -> postHashTag.getHashTag().getName()).toList();

        // 삭제된 해시태그 처리
        postHashTags.stream()
                .filter(tag -> !modifyHashtags.contains(tag.getHashTag().getName()))
                .forEach(BaseEntity::remove);

        // 새로 추가된 해시태그 처리
        List<String> newTagList = modifyHashtags.stream()
                .filter(tag -> !hashtags.contains(tag)).toList();

        createHashTags(post, newTagList);
    }
}
