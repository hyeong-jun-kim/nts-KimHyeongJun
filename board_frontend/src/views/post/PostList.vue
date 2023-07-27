<!-- 게시글 목록 화면 -->
<template>
    <div class="overflow-auto">
        <p class="mt-3">Current Page: {{ currentPage }}</p>

        <b-table id="my-table" :items="items" :per-page="perPage" :current-page="currentPage" small></b-table>
        <b-pagination v-model="currentPage" :total-rows="rows" :per-page="perPage" :aria-controls="my - table"
            align="center"></b-pagination>
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

            perPage: 3,
            currentPage: this.$route.query.page ? this.$route.query.page : 1,
            items: [
                { id: 1, first_name: 'Fred', last_name: 'Flintstone' },
                { id: 2, first_name: 'Wilma', last_name: 'Flintstone' },
                { id: 3, first_name: 'Barney', last_name: 'Rubble' },
                { id: 4, first_name: 'Betty', last_name: 'Rubble' },
                { id: 5, first_name: 'Pebbles', last_name: 'Flintstone' },
                { id: 6, first_name: 'Bamm Bamm', last_name: 'Rubble' },
                { id: 7, first_name: 'The Great', last_name: 'Gazzoo' },
                { id: 8, first_name: 'Rockhead', last_name: 'Slate' },
                { id: 9, first_name: 'Pearl', last_name: 'Slaghoople' }
            ],
        }
    },
    mounted() {
        this.getPostList()
    },
    computed: {
        rows() {
            return this.items.length
        },
    },
    methods: {
        async getPostList() {
            this.postService.getPostList(this.currentPage)
                .then(response => console.log(response.data.result))
                .catch(error => {
                    console.log(error);
                });
        }
    }
}
</script>