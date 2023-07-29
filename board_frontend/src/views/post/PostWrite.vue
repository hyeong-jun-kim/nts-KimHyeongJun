<!--게시글 작성 화면-->
<template>
    <div id="board-deatil">
        <warning-alert ref="warningAleartRef"></warning-alert> <!--컴포넌트 메서드 호출을 위해 ref 선언-->
        <div class="user-container">
            <tr>
                <th>
                    <div class="writer">
                        <b-form @submit.stop.prevent>
                            <label for="feedback-user">아이디</label>
                            <b-form-input v-model="writer" :state="idValidation" id="feedback-user"></b-form-input>
                            <b-form-invalid-feedback :state="idValidation">
                                아이디를 입력해주세요.
                            </b-form-invalid-feedback>
                        </b-form>
                    </div>
                </th>
                <th>
                    <div class="password">
                        <b-form @submit.stop.prevent>
                            <label for="text-password">비밀번호</label>
                            <b-form-input v-model="password" :state="pwdValidation" id="text-password"></b-form-input>
                            <b-form-invalid-feedback :state="pwdValidation">
                                비밀번호를 입력해주세요.
                            </b-form-invalid-feedback>
                        </b-form>
                    </div>
                </th>
            </tr>
        </div>
        <div class="board-contents">
            <b-form-input v-model="title" placeholder="제목을 입력해주세요."></b-form-input>
        </div>
        <div class="board-contents">
            <b-form-textarea v-model="content" id="textarea-rows" placeholder="내용을 입력해주세요." cols="3"
                rows="10"></b-form-textarea>
        </div>
        <div class="common-buttons">
            <b-button size="lg" v-on:click="writePost" variant="success">작성</b-button>
        </div>
    </div>
</template>
<script>
export default {
    inject: ['postService'], // inject를 통한 postService 주입
    data() {
        return {
            writer: '',
            password: '',
            title: '',
            content: ''
        }
    },
    computed: {
        idValidation() {
            return this.writer.length > 0 && this.writer.length <= 10
        },
        pwdValidation() {
            return this.password.length > 4 && this.password.length < 13
        }
    },
    methods: {
        writePost() {
            if(!this.validateWritePost())
                return;

            const map = new Map()
            map.set("writer", this.writer)
            map.set("password", this.password)
            map.set("title", this.title)
            map.set("content", this.content)

            const response = this.postService.writePost(Object.fromEntries(map))
            console.log("response: " + response)
        },

        // 글 작성시 검증 메서드
        validateWritePost(){
            const warningAlert = this.$refs.warningAleartRef;
            // 검증 : 작성자 이름 길이 (1 <= x <= 10)
            if (this.writer.length < 1 || this.writer.length > 10) {
                warningAlert.showAlert("작성자 이름 길이는 1이상 10이하만 가능합니다.");
                return false;
            }

            // 검증 : 비밀번호 (4 <= x <= 15)
            if (this.password.length < 4 || this.password.length > 15) {
                warningAlert.showAlert("비밀번호 길이는 4이상 15이하만 가능합니다.");
                return false;
            }

            // 검증 : 제목 (1 <= x <= 30)
            if (this.title.length < 1 || this.title.length > 30) {
                warningAlert.showAlert("제목의 길이는 1이상 30이하만 가능합니다.");
                return false;
            }

            // 검증 : 본문 (1 <= x <= 500)
            if (this.content.length < 1 || this.content.length > 30) {
                warningAlert.showAlert("본문의 길이는 1이상 500이하만 가능합니다.");
                return false;
            }

            return true;
        }
    }
};
</script>