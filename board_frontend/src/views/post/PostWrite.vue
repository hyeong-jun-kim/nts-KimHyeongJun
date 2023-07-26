<template>
    <div id="board-deatil">
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
            <b-form-textarea v-model="content" id="textarea-rows" placeholder="내용을 입력해주세요." cols="3" rows="10"></b-form-textarea>
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
            return this.writer.length > 0 && this.writer.length < 10
        },
        pwdValidation() {
            return this.password.length > 4 && this.password.length < 13
        }
    },
    methods: {
        writePost() { // writeService 사용
            const form = new FormData();
            form.append("writer", this.writer);
            form.append("password", this.password);
            form.append("title", this.title);
            form.append("content", this.content);
     
            const response = this.postService.writePost(form)
            console.log("response: " + response);
        }
    }
};
</script>