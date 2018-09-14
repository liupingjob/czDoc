/*
路由器模块
 */
import Vue from 'vue'
import VueRouter from 'vue-router'


import register from '../components/register/Hello.vue'
import login from '../components/login/login.vue'
import main from '../components/main/main.vue'
import forget from '../components/forget/forget.vue'

Vue.use(VueRouter)


export default new VueRouter({
  routes: [
    {
      path: '/register',
      component: register
    },
    {
      path: '/login',
      name: login,
      component: login
    },
    {
      path: '/main',
      name: 'main',
      component: main
    },
    {
      path: '/forget',
      name: 'forget',
      component: forget
    },
    {
      path: '/',
      redirect: '/main'
    },
  ]
})
