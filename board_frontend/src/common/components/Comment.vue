<!-- 댓글 컴포넌트 -->
<template>
    <div>
        <div class="comment-content" style="text-align: left">
            <b-row class="p-3" gutters align-h="between">
                <b-col class="text-no-wrap ml-3" cols="1">{{ comment.writer }}</b-col>

                <!-- 기존 댓글 내용 표시 -->
                <b-col v-if="!editingMode" class="text-left ml-5" cols="7">{{ comment.content }}</b-col>

                <!-- 댓글 수정 textarea 표시 -->
                <b-col v-else class="text-left ml-5" cols="7">
                    <b-form-textarea id="textarea" v-model="editedContent" rows="2" max-rows="5"></b-form-textarea>
                    <button @click="saveEditedComment">수정</button>
                    <button @click="cancelEditing">취소</button>
                </b-col>

                <b-col class="text-no-wrap" cols="2">{{ comment.createdAt }}</b-col>
                <!--수정 버튼-->
                <b-icon icon="pencil-square" class="border rounded" @click="modifyEvent"></b-icon>
                <!--삭제 버튼-->
                <b-icon icon="x-lg" class="border rounded" @click="deleteEvent"></b-icon>
            </b-row>

            <!--댓글 수정, 삭제시 비밀번호 인증을 위한 컴포넌트 (아이콘 누르면 활성화)-->
            <password v-if="showPassword" :commentId="data.comment.commentId" :eventType="eventType"
                @password-event="handlePasswordEvent"></password>

            <!--댓글 삭제 확인창 모달-->
            <b-modal v-model="deleteMode" id="bv-modal-example" hide-footer>
                <template #modal-title>
                    삭제 알림
                </template>
                <div class="d-block text-center">
                    <h3>정말 댓글을 삭제하시겠습니까?</h3>
                </div>
                <b-button class="mt-3" block @click="deleteComment">삭제</b-button>
                <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-example')">취소</b-button>
            </b-modal>
        </div>
    </div>
</template>

<script>
export default {
    inject: ['commentService'],
    props: ['data'],
    data() {
        return {
            showPassword: false,
            eventType: '',
            editingMode: false,
            deleteMode: false,
            editedContent: this.data.comment.content,
            comment: this.data.comment,
            password: 0,
        }
    },
    methods: {
        modifyEvent() {
            if (!this.showPassword) {
                this.eventType = "modify"
                this.showPassword = true
            } else
                this.showPassword = false
        },
        deleteEvent() {
            if (!this.showPassword) {
                this.eventType = "delete"
                this.showPassword = true;
            } else
                this.showPassword = false
        },
        cancelEditing() {
            this.editedComment = this.comment
            this.editingMode = false
        },
        handlePasswordEvent(passwordType, password) { // Password 컴포넌트에서 받은 이벤트 처리
            if (passwordType == "modify") {
                this.editingMode = true
            } else if (passwordType == "delete") {
                this.deleteMode = true
            }

            this.showPassword = false; // 기존에 비밀번호 창 닫기
            this.password = password
        },
        async saveEditedComment() { // 댓글 수정

            const map = new Map()
            map.set("commentId", this.comment.commentId)
            map.set("password", this.password)
            map.set("content", this.editedContent)
            

            await this.commentService.modifyComment(Object.fromEntries(map))
                .then(response => {
                    if (response.data.code == 1001) {
                        this.editingMode = false
                        this.$emit('modify-comment', this.comment.commentId, this.editedContent) // 부모 컴포넌트(PostDetail)로 이벤트 발생시켜 댓글 갱신하기
                        alert("댓글 수정이 완료되었습니다.")
                    }
                }).catch(error => {
                    console.log(error)
                })
        },
        async deleteComment() { // 댓글 삭제
            const map = new Map()
            map.set("commentId", this.comment.commentId)
            map.set("password", this.password)

            await this.commentService.deleteComment(Object.fromEntries(map))
                .then(response => {
                    if (response.data.code == 1002) {
                        this.deleteMode = false
                        this.$emit('delete-comment', this.comment.commentId)
                        alert("댓글 삭제가 완료되었습니다.")
                    }
                }).catch(error => {
                    console.log(error)
                })
        }
    }
}
</script>