import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import './assets/common.css'

import Password from './common/components/Password'
import WarningAlert from './common/components/WarningAlert'

import axios from 'axios'
import postService from './services/postService'
import commentService from './services/commentService'
import likeService from './services/likeService'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// 부트스트랩 설정
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

Vue.config.productionTip = false

// axios 설정
Vue.prototype.$http = axios

// 컴포넌트 설정
Vue.component("password", Password)
Vue.component("WarningAlert", WarningAlert)

new Vue({
  router,
  render: h => h(App),
  provide : {
    postService: new postService,  // 부모 컴포넌트에서 설정
    commentService: new commentService,
    likeService: new likeService,
  }
}).$mount('#app')