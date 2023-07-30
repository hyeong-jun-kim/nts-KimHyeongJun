<template>
  <div id="app">
    <div id="content" class="content">
      <PageHeader/> <!--헤더 컴포넌트-->
      <router-view/> <!--페이지 이동 표시-->
      <PageFooter/> <!--푸터 컴포넌트-->
    </div>
  </div>
</template>

<script>
  import PageHeader from './common/components/PageHeader'
  import PageFooter from './common/components/PageFooter'

  export default {
    name: 'App',
    components: {
      PageHeader,
      PageFooter
    }
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
