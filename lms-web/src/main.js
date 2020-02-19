import Vue from 'vue'
import App from './App.vue'
import router from './router'

var axios = require('axios')
axios.defaults.baseURL = 'http://locahost:8080/api'
Vue.prototype.$axios = axios
Vue.config.productionTip = false

new Vue({
  el:'#app',
  router,
  render: h => h(App),
}).$mount('#app')
