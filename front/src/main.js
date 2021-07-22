import Vue from 'vue'
import App from './App.vue'
import VueRouter from "vue-router"
import router from "./router/index.js"
import Element from 'element-ui'

Vue.use(Element);
Vue.use(VueRouter);
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
