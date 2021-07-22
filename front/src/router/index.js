import Vue from 'vue'
import Router from 'vue-router'

import Main from '../views/Main'
import Login from '../views/Login'

Vue.use(Router)

export default new Router({
  routes:[
    {
      name:'main',
      path:'/',
      component:Main,
      children:[]
    },
    {
      name:'login',
      path:'/login',
      component:Login
    }
  ]
})
