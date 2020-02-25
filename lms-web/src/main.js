import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from "./store";

var axios = require('axios')

// axios.defaults.baseURL = 'http://localhost:8081'
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI)
axios.defaults.withCredentials = true
axios.defaults.headers['Content-Type'] = 'application/json'
axios.defaults.headers['Access-Control-Allow-Origin'] = 'http://localhost:8081'
axios.defaults.headers['Access-Control-Allow-Credentials'] = 'true'


new Vue({
    el: '#app',
    store,
    router,
    render: h => h(App),
    comonents: {App},
    template: '<App/>',

}).$mount('#app')
