<!-- 게시글 목록 화면 -->
<template>
    <div class="overflow-auto">
        <p class="mt-3">Current Page: {{ currentPage }}</p>

        <b-table id="my-table" ref="table" :items="items" :pages="pageList" :current-page="currentPage"
            medium></b-table>
        <b-pagination-nav v-model="currentPage" :link-gen="linkGen" :total-rows="rows" :per-page="size"
            align="center" :number-of-pages="pageList.length" first-number use-router @change="getPostList"></b-pagination-nav>
    </div>
</template>
  
<script>
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
            items: []
        }
    },
    created() {
        console.log(this.currentPage)
        this.getPostList(this.currentPage)
    },
    computed: {
        rows() {
            return this.items.length
        },
    },
    methods: {
         async getPostList(page) { // 게시글 목록 받아오기
             await this.postService.getPostList(page)
                .then(response => {
                    const result = response.data.result;
                    
                    // 게시글 내용 채우기
                    for (var i = 0; i < result.contents.length; i++) {
                        const content = result.contents[i]
                        const item = new Object()
                        item.제목 = content.title
                        item.작성자 = content.writer
                        item.작성일 = content.createdAt
                        item.좋아요 = content.likeCnt
                        item.조회수 = content.likeCnt

                        this.items[i] = item
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

                    this.$refs.table.refresh()
                    console.log(this.items)
                    console.log(result.contents)
                })
                .catch(error => {
                    console.log(error);
                });
        },
        linkGen(pageNum) { // 버튼 클릭시 페이지 이동 함수
            return {
                path: '/posts',
                query: { page: pageNum }
            }
        },
    }
}
</script>