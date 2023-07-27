<template>
  <div class="writer">
    <b-form @submit.stop.prevent>
      <label for="feedback-user">아이디</label>
      <b-form-input v-model="userId" :state="validation" id="feedback-user"></b-form-input>
      <b-form-invalid-feedback :state="validation">
        아이디를 입력해주세요.
      </b-form-invalid-feedback>
      <!-- <b-form-valid-feedback :state="validation">
        Looks Good.
      </b-form-valid-feedback> -->
    </b-form>
  </div>
</template>
<script>
export default {
  data() {
    return {
      userId: ''
    }
  },
  computed: {
    validation() {
      return this.userId.length >0 && this.userId.length < 10
    }
  }
};
</script>