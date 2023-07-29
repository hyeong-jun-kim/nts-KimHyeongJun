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
    inject: ['commentService'],
    props: ['commentId', 'postId', 'eventType'],
    data() {
        return {
            password: ''
        }
    },
    methods: {
        async handleClick() {
            const validateCheck = await this.validatePassword()
            if (!validateCheck) {
                alert("비밀번호가 틀립니다.")
                return;
            }

            if(this.eventType == "modify"){ // 댓글 수정
                this.$emit('password-event', "modify", this.password) // Comment 컴포넌트로 전달하기
            }else if(this.eventType == "delete"){ // 댓글 삭제
                this.$emit('password-event', "delete", this.password)
            }else if(this.eventType == "modifyPost"){
                this.$emit('password-event', "modify", this.password) // Post 컴포넌트로 전달하기
            }else if(this.eventType == "deletePost"){
                this.$emit('password-event', "delete", this.password)
            }
        },
        async validatePassword() {
            const map = new Map()
            map.set("commentId", this.commentId)
            map.set("password", this.password)

            const check = await this.commentService.validatePassword(Object.fromEntries(map))
            .then(response => {
                console.log(response)
                if(response.data.code == 1003){
                    console.log("성공")
                    return true
                }else
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