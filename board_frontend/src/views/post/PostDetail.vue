<!-- 게시글 상세보기 화면 -->
<template>
    <div>
        <!--제목, 부가정보-->
        <div class="post-detail-contents">
            <b-card :title="postTitle" class="text-left">
                <b-row>
                    <b-col cols="5">닉네임: {{ writer }}</b-col>
                    <b-col cols="9">작성일지: {{ createdAt }}</b-col>
                    <b-col class="text-right">조회수: {{ viewCnt }}</b-col>
                    <b-col class="text-right">좋아요: {{ likeCnt }}</b-col>
                </b-row>
            </b-card>
            <!--본문-->
            <b-form-textarea class="ml-2 mt-3" v-model="content" id="textarea-rows" cols="3" rows="10" plaintext
                :value="text"></b-form-textarea>
            <!--해시태그-->
            <div class="d-flex justify-content-start ml-2">
                <div v-for="(item, index) in hashtags" :key="index" class="mb-4 mr-3"
                    style="display: inline">
                    {{ "# " + item}}
                </div>
            </div>
            <!--좋아요, 싫어요-->
            <div class="m-5" style="font-size: 3.5rem">
                {{ likeCnt }} <b-icon icon="hand-thumbs-up-fill" class="border rounded p-2 ml-2 mr-2"
                    style="cursor: pointer" @click="handleLikeClick">
                </b-icon>
                <b-icon icon="hand-thumbs-down" class="border rounded p-2 ml-2 mr-2" style="cursor: pointer"
                    @click="handleDisLikeClick"></b-icon> {{ disLikeCnt }}
            </div>
            <!--수정, 삭제 버튼-->
            <div class="d-flex justify-content-end mb-2" style="font-size: 3.5rem">
                <b-button class="mr-2" @click="handleModifyClick">수정</b-button>
                <b-button class="ml-2" @click="handleDeleteClick">삭제</b-button>
            </div>
            <!--게시글 삭제시 비밀번호 인증을 위한 컴포넌트 (버튼 누르면 활성화)-->
            <password v-if="showPassword" :postId="postId" :eventType="eventType" @password-event="handlePasswordEvent">
            </password>
            <!--댓글-->
            <div class="mb-3 text-left">
                전체 댓글 {{ comments.length }}개
            </div>
            <comment v-for="data in comments" @modify-comment="modifyCommentEvent" @delete-comment="deleteCommentEvent"
                :key="data.comment.commentId" :data="data" />
            <!--댓글 작성하기-->
            <comment-write @comments-to-post="handleComments" :postId="postId" />
        </div>
    </div>
</template>

<script>
import Comment from '../../common/components/Comment.vue';
import CommentWrite from '../../common/components/CommentWrite.vue';

export default {
    inject: ['postService', 'likeService'],
    components: { // 지역 컴포넌트 선언
        'comment': Comment,
        'comment-write': CommentWrite,
    },
    data() {
        return {
            postTitle: '',
            writer: '',
            createdAt: '',
            viewCnt: 0,
            commentCnt: 0,
            content: '',
            likeCnt: 0,
            disLikeCnt: 0,
            comments: [],
            hashtags: [],
            postId: this.$route.params.postId,
            showPassword: false,
            eventType: ''
        }
    },
    mounted() {
        this.getPost(this.postId)
    },
    methods: {
        async getPost(postId) { // 게시글 상세정보 받아오기
            await this.postService.getPost(postId)
                .then(response => {
                    const result = response.data.result;

                    // 게시글 정보 채우기
                    this.postId = result.postId
                    this.postTitle = result.title
                    this.writer = result.writer
                    this.createdAt = result.createdAt
                    this.viewCnt = result.viewCount
                    this.content = result.content
                    this.likeCnt = result.likeCnt
                    this.unLikeCnt = result.unLikeCnt

                    // 댓글 목록 받아오기
                    this.comments = result.comments

                    // 해시태그 받기
                    this.hashtags = result.hashtags

                    console.log(this.comments)
                })
                .catch(error => {
                    console.log(error);
                });
        },
        async deletePost(password) { // 게시글 삭제하기
            const map = new Map()
            map.set("postId", this.postId)
            map.set("password", password)

            await this.postService.deletePost(Object.fromEntries(map))
                .then(response => {
                    if (response.data.code == 1007) {
                        alert("게시글이 삭제되었습니다.")
                        this.$router.push('/') // 게시글 목록으로 이동 
                    }
                }).catch(error => {
                    console.log(error)
                })
        },
        /**
         * 댓글관련 함수
         */
        handleComments(comments) { // 댓글 작성후 댓글 목록 reload
            this.comments = comments
            console.log(comments)
        },
        modifyCommentEvent(commentId, editedContent) {
            const modifyComment = this.comments.find((data => {
                return data.comment.commentId == commentId
            }));

            modifyComment.comment.content = editedContent
        },
        deleteCommentEvent(commentId) {
            const deleteComment = this.comments.find((data => {
                return data.comment.commentId == commentId
            }));

            deleteComment.comment.content = "삭제된 댓글입니다."
        },
        /**
         * 좋아요 함수
         */
        handleLikeClick() {
            this.createLike("LIKE")
        },
        handleDisLikeClick() {
            this.createLike("UNLIKE")
        },
        async createLike(likeType) {
            const map = new Map()
            map.set("postId", this.postId)
            map.set("likeType", likeType)

            await this.likeService.createLike(Object.fromEntries(map))
                .then(response => {
                    const code = response.data.code
                    if (code == 1004)
                        this.likeCnt++
                    else if (code == 1005)
                        this.disLikeCnt++;
                    else if (code == 2008)
                        alert("이미 좋아요/싫어요를 누른 게시글입니다.")
                }).catch(error => {
                    console.log(error)
                })
        },
        /**
         * 게시글 수정, 삭제 관련
         */
        handleModifyClick() {
            this.$router.push('/post/modify/' + this.postId) // 수정 페이지로 이동
        },
        handleDeleteClick() {
            if (!this.showPassword) {
                this.showPassword = true
                this.eventType = "deletePost"
            } else
                this.showPassword = false
        },
        handlePasswordEvent(eventType, password) {
            if (eventType == "delete") {
                this.deletePost(password)
            }
        },
    },
}
</script>