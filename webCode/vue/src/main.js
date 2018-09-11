// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
// import router from 'vue-router';
// Vue.use(router)
import 'jquery'
import App from './App'
import router from './router'
import VueResource from 'vue-resource'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './common/stylus/fonts.css'



Vue.use(ElementUI)
Vue.use(VueResource)

Vue.config.productionTip = false


new Vue({
  el: '#app',
  template: '<App/>',
  components: { App },
  router,
})
