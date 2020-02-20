import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8081'
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI)

new Vue({
  el:'#app',
  router,
  render: h => h(App),
  comonents:{App},
  template:'<App/>',

}).$mount('#app')
