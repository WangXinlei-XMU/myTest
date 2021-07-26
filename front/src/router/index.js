import Vue from 'vue'
import Router from 'vue-router'

import Main from '../views/Main'
import Login from "../views/Login";
import Register from "../views/Register";
import Person from "../views/Person";
import Info from "../components/Info";
import Setting from "../components/Setting";
import Privacy from "../components/Privacy";
import Like from "../components/Like";
import AManage from "../components/AManage";
import CManage from "../components/CManage";

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
      component:Register,
    },
    {
      name:'person',
      path:'/person',
      component:Person,
      children:[
        {
          name: 'info',
          path: '/info',
          component: Info
        },
        {
          name: 'setting',
          path: '/setting',
          component: Setting
        },
        {
          name: 'privacy',
          path: '/privacy',
          component: Privacy
        },
        {
          name: 'like',
          path: '/like',
          component: Like
        },
        {
          name: 'aManage',
          path: '/aManage',
          component: AManage
        },
        {
          name: 'cManage',
          path: '/cManage',
          component: CManage
        }
      ]
    }
  ]
})
