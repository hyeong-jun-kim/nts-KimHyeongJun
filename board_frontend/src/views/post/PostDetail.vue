<!-- 게시글 상세보기 화면 -->
<template>
    <div>
        <!--제목, 부가정보-->
        <div class="post-detail-contents">
            <b-card :title="postTitle" class="text-left">
                <b-row align-h="between">
                    <b-col cols="5">닉네임: {{ writer }}</b-col>
                    <b-col class="text-left" cols="10">작성일지: {{ createdAt }}</b-col>
                    <b-col class="text-right" cols="1">조회수: {{ viewCnt }}</b-col>
                    <b-col class="text-right" cols="1">댓글수: {{ commentList.length }}</b-col>
                </b-row>
            </b-card>
        </div>
        <!--본문-->
        <div class="post-detail-contents"> 
            <b-card class="text-left">
                <b-card-text>
                    {{ content }}
                </b-card-text>
            </b-card>
        </div>
        <!--좋아요, 싫어요-->
        <div class="p-4" style="font-size: 3.5rem">
            {{ likeCnt }} <b-icon icon="hand-thumbs-up-fill" class="border rounded p-2 ml-2 mr-2">
            </b-icon>
            <b-icon icon="hand-thumbs-down" class="border rounded p-2 ml-2 mr-2"></b-icon> {{ unLikeCnt }}
        </div>
        <!--댓글-->
        <div class="post-detail-contents">
            <div class="mb-3 text-left">
                전체 댓글 2개
            </div>
            <b-card>
                <b-row align-h="between">
                    <b-col cols="1">닉네임</b-col>
                    <b-col class="text-left" cols="9">내용</b-col>
                    <b-col cols="1">작성일지</b-col>
                </b-row>
            </b-card>
        </div>
    </div>
</template>
<script>
export default {
    inject: ['postService'],
    data() {
        return {
            postTitle: '',
            writer: '',
            createdAt: '',
            viewCnt: 0,
            commentCnt: 0,
            content: '',
            likeCnt: 0,
            unLikeCnt: 0,
            commentList: [],
            postId: this.$route.params.postId
        }
    },
    created() {
        this.getPost(this.postId)
    }, methods: {
        async getPost(postId) { // 게시글 상세정보 받아오기
            await this.postService.getPost(postId)
                .then(response => {
                    const result = response.data.result;

                    // 게시글 정보 채우기
                    this.postTitle = result.title
                    this.writer = result.writer
                    this.createdAt = result.createdAt
                    this.viewCnt = result.viewCount
                    this.content = result.content
                    this.likeCnt = result.likeCnt
                    this.unLikeCnt = result.unLikeCnt

                    console.log(this.postTitle)
                })
                .catch(error => {
                    console.log(error);
                });
        }
    }
}
</script>