<template>
    <div class="password">
        <b-form @submit.stop.prevent>
            <label for="text-password">비밀번호</label>
            <b-form-input v-model="password" :state="validation" id="text-password"></b-form-input>
            <b-form-invalid-feedback :state="validation">
                비밀번호를 입력해주세요. 
            </b-form-invalid-feedback>
            <!-- <b-form-text id="password-help-block">
                Your password must be 8-20 characters long, contain letters and numbers, and must not
                contain spaces, special characters, or emoji.
            </b-form-text> -->
        </b-form>
    </div>
</template>
  
<script>
export default {
    data() {
        return {
            password: ''
        }
    },
    computed: {
        validation() {
            return this.password.length > 4 && this.password.length < 13
        }
    }
}
</script>