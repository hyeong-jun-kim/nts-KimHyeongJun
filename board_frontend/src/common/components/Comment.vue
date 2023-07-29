<!-- 댓글 컴포넌트 -->
<template>
    <div>
        <div class="comment-content" style="text-align: left">
            <b-row class="p-3" gutters align-h="between">
                <b-col class="text-no-wrap ml-3" cols="1">{{ data.comment.writer }}</b-col>
                <b-col class="text-left ml-5" cols="7">{{ data.comment.content }}</b-col>
                <b-col class="text-no-wrap" cols="2">{{ data.comment.createdAt }}</b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    props: ['data']
}
</script>