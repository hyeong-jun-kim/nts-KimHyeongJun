<!-- 게시글 상세보기 화면 -->
<template>
    <div>
        <div class="post-detail-contents">
            <b-card title="제목" class="text-left">
                <b-row align-h="between">
                    <b-col cols="1">닉네임: ㅇㅇ</b-col>
                    <b-col class="text-left" cols="9">작성일지: 2022-10-22 10:36:22</b-col>
                    <b-col class="text-right" cols="1">조회수:1</b-col>
                    <b-col class="text-right" cols="1">댓글:2</b-col>
                </b-row>
            </b-card>
        </div>
        <div class="post-detail-contents">
            <b-card class="text-left">
                <b-card-text>
                    내용
                </b-card-text>
            </b-card>
        </div>
        <div class="p-4" style="font-size: 3.5rem">
            1 <b-icon icon="hand-thumbs-up-fill" class="border rounded p-2 ml-2 mr-2">
            </b-icon>
            <b-icon icon="hand-thumbs-down" class="border rounded p-2 ml-2 mr-2"></b-icon> 2
        </div>
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
    export default{
        intject: ['postService'],
        data() {
            
        }
    }
</script>