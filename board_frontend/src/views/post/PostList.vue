<!-- 게시글 목록 화면 -->
<template>
    <div class="overflow-auto">
        <!--전체 게시글 수, 전체 댓글 수-->
        <div class="d-flex justify-content-start ml-5 mb-3">
            <p class="mr-3">전체 게시글 수: {{ postCount }}</p>
            <p>전체 댓글 수: {{ commentCount }}</p>
        </div>
        <!--글쓰기 버튼-->
        <div class="d-flex justify-content-start ml-5 mb-3">
            <b-button variant="primary" @click="goToWritePage">글쓰기</b-button>
        </div>
        <!--게시글 목록 테이블-->
        <b-table class="table-pagination" ref="table" :items="items" :fields="tableFields" :pages="pageList"
            :current-page="currentPage" medium @row-clicked="rowClickHandler"></b-table>

        <!--페이지 이동 버튼-->
        <b-pagination-nav v-model="currentPage" :link-gen="linkGen" :total-rows="rows" :per-page="size" align="center"
            :number-of-pages="totalPage" first-number use-router :show-ellipsis="false"
            @change="getPostPagingList"></b-pagination-nav>
    </div>
</template>
  
<script>
import router from '@/router';

export default {
    inject: ['postService'],
    data() {
        return {
            postCount: 0,
            commentCount: 0,
            totalPage: 0,
            page: 0,
            size: 0,
            currentPage: this.$route.query.page ? this.$route.query.page : 1,
            pageList: [],
            postIdList: [],
            items: [],
            tableFields: [
                { key: 'postId', label: '번호' },
                { key: 'writer', label: '작성자', },
                { key: 'title', label: '제목' },
                { key: 'createdAt', label: '작성일' },
                { key: 'likeCnt', label: '좋아요' },
                { key: 'viewCnt', label: '조회수' },
                // ...
            ]
        }
    },
    created() {
        this.getPostPagingList(this.currentPage)
    },
    computed: {
        rows() {
            return this.items.length
        },
    },
    methods: {
        async getPostPagingList(page) { // 게시글 목록 받아오기
            await this.postService.getPostPagingList(page)
                .then(response => {
                    const result = response.data.result;
                    this.postIdList = []
                    this.items = []

                    // 게시글 내용 채우기
                    for (var i = 0; i < result.contents.length; i++) {
                        const content = result.contents[i]
                        const item = new Object()
                        item.postId = content.postId
                        item.writer = content.writer

                        // 새로 생성된 게시글이면 제목 앞에 New 추가
                        if (content.new)
                            item.title = "[New] " + content.title
                        else
                            item.title = content.title

                        item.createdAt = content.createdAt
                        item.likeCnt = content.likeCnt
                        item.viewCnt = content.viewCount

                        this.items[i] = item
                        this.postIdList[i] = content.postId
                    }
                    // 페이징에 필요한 값 받아오기
                    this.totalPage = result.totalPage
                    this.page = result.page
                    this.size = result.size
                    this.pageList = result.pageList

                    this.postCount = result.postCount;
                    this.commentCount = result.commentCount;

                    this.$refs.table.refresh() // 테이블 새로고침
                })
                .catch(error => {
                    console.log(error);
                });
        },
        linkGen(pageNum) { // 버튼 클릭시 페이지 이동
            return {
                path: '/',
                query: { page: pageNum }
            }
        },
        rowClickHandler(item) { // 테이블 행 클릭시 게시글 상세보기로 이동
            router.push({
                path: '/post/' + item.postId,
            });
        },
        goToWritePage() { // 글쓰기 페이지로 이동
            this.$router.push('/post/write')
        }
    }
}
</script>
<style>
.table-pagination {
    cursor: pointer;
}
</style>