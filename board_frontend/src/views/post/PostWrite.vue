<template>
    <div id="board-deatil">
        <div class="user-container">
            <tr>
                <th>
                    <div class="writer">
                        <b-form @submit.stop.prevent>
                            <label for="feedback-user">아이디</label>
                            <b-form-input v-model="userId" :state="idValidation" id="feedback-user"></b-form-input>
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
            <b-form-textarea id="textarea-rows" placeholder="내용을 입력해주세요." cols="3" rows="10"></b-form-textarea>
        </div>
        <div class="common-buttons">
            <b-button size="lg" variant="success">작성</b-button>
        </div>
    </div>
</template>
<script>
export default {
    data() {
        return {
            title: '',
            userId: '',
            password: ''
        }
    },
    computed: {
        idValidation() {
            return this.userId.length > 0 && this.userId.length < 10
        },
        pwdValidation() {
            return this.password.length > 4 && this.password.length < 13
        }
    }
};
</script>