<!--경고 알림 컴포넌트-->
<template>
  <div class="alert">
    <b-alert
      :show="dismissCountDown"
      dismissible
      fade
      variant="warning"
      @dismiss-count-down="countDownChanged"
    >
      {{text}}
    </b-alert>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        text: '',
        dismissSecs: 5,
        dismissCountDown: 0,
        showDismissibleAlert: false
      }
    },
    methods: {
      countDownChanged(dismissCountDown) {
        this.dismissCountDown = dismissCountDown
      },
      showAlert(text) {
        this.text = text
        this.dismissCountDown = this.dismissSecs
      }
    }
  }
</script>