import Vue from 'vue'
import Router from 'vue-router'

import Main from '../views/Main'
import Login from "../views/Login";
import Register from "../views/Register";
import Info from "../views/Info";
import UserInfo from "../views/UserInfo";
import Me from "../views/Me";
import Article from "../views/Article"
import Admin from "../views/Admin"


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
    },
    {
      name:'register',
      path:'/register',
      component:Register
    },
    {
      name:'info',
      path:'/info',
      component:Info
    },
    {
      name:'userInfo',
      path:'/userInfo',
      component:UserInfo
    },
    {
      name:'me',
      path:'/me',
      component:Me
    },
    {
      name:'article',
      path:'/article',
      component:Article
    },
    {
      name:'admin',
      path:'/admin',
      component:Admin
    }

  ]
})
