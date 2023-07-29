<!--댓글 비밀번호 검증 컴포넌트-->
<template>
    <div>
        <div class="password-content">
            <b-row class="d-flex justify-content-end p-3">
                <b-col cols="auto">
                    <b-form-input v-model="password" type="password" placeholder="비밀번호" size="sm"></b-form-input>
                </b-col>
                <b-col cols="auto">
                    <b-button @click="handleClick">확인</b-button>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
export default {
    inject: ['postService', 'commentService'],
    props: ['commentId', 'postId', 'eventType'],
    data() {
        return {
            password: ''
        }
    },
    methods: {
        async handleClick() {
            console.log(this.commentId)
            console.log(this.postId)
            console.log(this.eventType)

            if (this.commentId != null) { // 댓글 비밀번호 검증    
                const validateCheck = await this.validateCommentPassword()
                if (!validateCheck) {
                    alert("비밀번호가 틀립니다.")
                    return;
                }
                if (this.eventType == "modifyComment") {
                    this.$emit('password-event', "modify", this.password) // Comment 컴포넌트로 전달하기
                } else if (this.eventType == "deleteComment") {
                    this.$emit('password-event', "delete", this.password)
                }

            } else if (this.postId != null) { // 게시글 비밀번호 검증
                const validateCheck = await this.validatePostPassword()
                if (!validateCheck) {
                    alert("비밀번호가 틀립니다.")
                    return;
                }

                if (this.eventType == "modifyPost") {
                    this.$emit('password-event', "modify", this.password) // Post 컴포넌트로 전달하기
                } else if (this.eventType == "deletePost") {
                    this.$emit('password-event', "delete", this.password)
                }
            }

        },
        async validateCommentPassword() {
            const map = new Map()
            map.set("commentId", this.commentId)
            map.set("password", this.password)

            const check = await this.commentService.validatePassword(Object.fromEntries(map))
                .then(response => {
                    console.log(response)
                    if (response.data.code == 1003) {
                        return true
                    } else
                        return false
                }).catch(error => {
                    console.log(error)
                    return false
                })

            return check
        },
        async validatePostPassword() {
            const map = new Map()
            map.set("postId", this.postId)
            map.set("password", this.password)

            const check = await this.postService.validatePassword(Object.fromEntries(map))
                .then(response => {
                    console.log(response)
                    if (response.data.code == 1003) {
                        return true
                    } else
                        return false
                }).catch(error => {
                    console.log(error)
                    return false
                })

            return check
        }
    }
}
</script>