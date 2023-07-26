import Vue from 'vue'
import App from './App.vue'
import router from './router'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import './assets/common.css'

import Writer from './common/components/Writer'
import Password from './common/components/Password'

import axios from 'axios'
import postService from './services/postService'

// 부트스트랩 설정
// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
// Optionally install the BootstrapVue icon components plugin
Vue.use(IconsPlugin)

Vue.config.productionTip = false

// axios 설정
Vue.prototype.$http = axios

// 컴포넌트 설정
Vue.component("writer", Writer)
Vue.component("password", Password)

new Vue({
  router,
  render: h => h(App),
  provide : {
    postService: new postService,  // 부모 컴포넌트에서 설정
  }
}).$mount('#app')
