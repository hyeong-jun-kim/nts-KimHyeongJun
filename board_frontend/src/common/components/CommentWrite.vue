<template>
    <div>
        <b-card>
            <b-row>
                <b-form-input class="col-2" v-model="writer" type="text" placeholder="아이디" maxlength="10"></b-form-input>
                <b-form-input class="col-2" v-model="password" type="password" placeholder="비밀번호" maxlength="15"
                    pattern="^[a-zA-Z0-9]+$"></b-form-input>
            </b-row>
            <b-row>
                <b-form-textarea id="textarea" v-model="content" placeholder="댓글을 작성해보세요!" rows="3" max-rows="3"
                    style="resize: none"></b-form-textarea>
            </b-row>
            <b-button class="mt-3" variant="primary" @click="writeComment">등록</b-button>
        </b-card>
    </div>
</template>

<script>
export default {
    inject: ['commentService'],
    props: ['postId'],
    data() {
        return {
            writer: '',
            password: '',
            content: ''
        }
    },
    methods: {
        async writeComment() {
            if(!this.validateWriteComment()){
                return;
            }

            const map = new Map()
            map.set("postId", this.$props.postId)
            map.set("writer", this.writer)
            map.set("password", this.password)
            map.set("content", this.content)

            const response = await this.commentService.writeComment(Object.fromEntries(map))
            console.log(response.data.result)
            this.$emit('comments-to-post', response.data.result.comments, response.data.result.final) // 상위 컴포넌트로 값 전달하기
            this.resetContents()
            alert("댓글이 작성되었습니다.")
        },
        // 대슬 작성시 검증 메서드
        validateWriteComment(){
            // 검증 : 작성자 이름 길이 < 1
            if (this.writer.length < 1) {
                alert("닉네임은 최소 1글자 이상 입력하셔야 합니다.");
                return false;
            }

            // 검증 : 비밀번호 길이 < 4
            if (this.password.length < 4) {
                alert("비밀번호는 최소 4글자 이상 입력하셔야 합니다.");
                return false;
            }

            // 검증 : 댓글 내용 < 1
            if (this.content.length < 1) {
                alert("댓글 내용은 최소 1글자 이상 입력하셔야 합니다.");
                return false;
            }

            return true;
        },
        resetContents(){
            this.writer = '',
            this.password = '',
            this.content = ''
        }
    }
}
</script>