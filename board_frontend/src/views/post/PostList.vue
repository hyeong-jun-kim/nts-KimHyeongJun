<!-- 게시글 목록 화면 -->
<template>
    <div class="overflow-auto">
        <!--글쓰기 버튼-->
        <div class="d-flex justify-content-start ml-5 mb-3">
            <b-button variant="primary" @click="goToWritePage">글쓰기</b-button>
        </div>

        <!--게시글 목록 테이블-->
        <b-table id="my-table" ref="table" :items="items" :pages="pageList" :current-page="currentPage" medium
            style="cursor: pointer" @row-clicked="rowClickHandler"></b-table>

        <!--페이지 이동 버튼-->
        <b-pagination-nav v-model="currentPage" :link-gen="linkGen" :total-rows="rows" :per-page="size" align="center"
            :number-of-pages="pageList.length" first-number use-router @change="getPostPagingList"></b-pagination-nav>
    </div>
</template>
  
<script>
import router from '@/router';

export default {
    inject: ['postService'],
    data() {
        return {
            totalPage: 0,
            page: 0,
            size: 0,
            start: 0,
            end: 0,
            prev: false,
            next: false,
            currentPage: this.$route.query.page ? this.$route.query.page : 1,
            pageList: [],
            postIdList: [],
            items: [],
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

                    // 게시글 내용 채우기
                    for (var i = 0; i < result.contents.length; i++) {
                        const content = result.contents[i]
                        const item = new Object()
                        item.번호 = content.postId
                        item.작성자 = content.writer

                        // 새로 생성된 게시글이면 제목 앞에 New 추가
                        if (content.new)
                            item.제목 = "[New] " + content.title
                        else
                            item.제목 = content.title

                        item.작성일 = content.createdAt
                        item.좋아요 = content.likeCnt
                        item.조회수 = content.viewCount

                        this.items[i] = item
                        this.postIdList[i] = content.postId
                    }
                    // 페이징에 필요한 값 받아오기
                    this.totalPage = result.totalPage
                    this.page = result.page
                    this.start = result.start
                    this.end = result.end
                    this.size = result.size
                    this.prev = result.prev
                    this.next = result.next
                    this.pageList = result.pageList

                    this.$refs.table.refresh() // 테이블 새로고침
                })
                .catch(error => {
                    console.log(error);
                });
        },
        linkGen(pageNum) { // 버튼 클릭시 페이지 이동
            return {
                path: '/posts',
                query: { page: pageNum }
            }
        },
        rowClickHandler(record) { // 테이블 행 클릭시 게시글 상세보기로 이동
            router.push({
                path: '/post/' + record.번호,
            });
        },
        goToWritePage(){ // 글쓰기 페이지로 이동
            this.$router.push('/post/write')
        }
    }
}
</script>