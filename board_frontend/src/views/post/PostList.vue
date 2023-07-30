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
            @change="getPageContent"></b-pagination-nav>
        <!--제목, 작성자, 해시태그 검색-->
        <div class="row justify-content-center">
            <div class="input-group mb-3 col-5 mt-4">
                <b-form-select class="col-3" v-model="searchType" :options="searchOptions"></b-form-select>
                <input type="text" class="form-control mr-2" v-model="searchName" placeholder="검색어를 입력하세요."
                    @keydown.enter="searchPosts(1)" />
                <div class="input-group-append">
                    <button class="btn btn-primary mr-2" @click="searchPosts(1)">검색</button>
                    <button class="btn btn-primary" @click="searchReset">초기화</button>
                </div>
            </div>
        </div>
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
            pageList: [],
            postIdList: [],
            items: [],
            currentPage: this.$route.query.page ? this.$route.query.page : 1,
            searchType: 'title',
            searchName: null,
            tableFields: [
                { key: 'postId', label: '번호' },
                { key: 'writer', label: '작성자', },
                { key: 'title', label: '제목' },
                { key: 'createdAt', label: '작성일' },
                { key: 'likeCnt', label: '좋아요' },
                { key: 'viewCnt', label: '조회수' },
            ],
            searchOptions: [
                { value: 'title', text: '제목' },
                { value: 'content', text: '내용' },
                { value: 'writer', text: '작성자' },
                { value: 'hashtag', text: '해시태그' }
            ]
        }
    },
    mounted() {
        if (this.searchName == null)
            this.getPostPagingList(this.currentPage)
        else // 검색 게시글 목록
            this.searchPosts(this.currentPage)

        console.log(this.$route.query.s_name)
    },
    computed: {
        rows() {
            return this.items.length
        },
    },
    methods: {
        getPageContent(page) {
            if (this.searchName != null)
                this.searchPosts(page)
            else
                this.getPostPagingList(page)
        },
        async getPostPagingList(page) { // 게시글 목록 받아오기
            await this.postService.getPostPagingList(page)
                .then(response => {
                    const result = response.data.result;
                    this.loadedPosts(result)
                })
                .catch(error => {
                    console.log(error);
                });
        },
        async searchPosts(page) { // 게시글 검색목록 받아오기 : 제목, 내용, 작성자, 해시태그
            if (this.searchName == null) {
                alert('검색어를 입력해주세요');
                return;
            }

            await this.postService.searchPosts(page, this.searchType, this.searchName)
                .then(response => {
                    const result = response.data.result;
                    this.loadedPosts(result)
                })
                .catch(error => {
                    console.log(error);
                });
        },
        searchReset() {
            this.searchType = "title"
            this.searchName = null
            this.getPostPagingList(1)
        },
        loadedPosts(pageResult) {
            this.postIdList = []
            this.items = []

            // 게시글 내용 채우기
            for (var i = 0; i < pageResult.contents.length; i++) {
                const content = pageResult.contents[i]
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
            this.totalPage = pageResult.totalPage
            this.page = pageResult.page
            this.size = pageResult.size
            this.pageList = pageResult.pageList

            this.postCount = pageResult.postCount;
            this.commentCount = pageResult.commentCount;

            this.$refs.table.refresh()
        },
        linkGen(pageNum) { // 페이징 버튼 클릭시 페이지 이동
            if (this.searchName == null)
                return {
                    path: '/',
                    query: { page: pageNum }
                }
            else
                return { // 검색 페이지일때는 쿼리 따로 넘겨줘야함
                    path: '/',
                    query: { page: pageNum, searchType: this.searchType, searchName: this.searchName }
                }
        },
        rowClickHandler(item) { // 테이블 행 클릭시 게시글 상세보기로 이동
            router.push({
                path: '/post/' + item.postId,
            });
        },
        goToWritePage() {
            this.$router.push({
                path: '/post/write'
            });
        },
    }
}
</script>
<style>
.table-pagination {
    cursor: pointer;
}
</style>