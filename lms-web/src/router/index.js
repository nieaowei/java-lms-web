import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from "@/components/home/AppIndex";
import Login from "@/components/Login";

Vue.use(Router)

export default new Router(
    {
        routes:[
            {
                name: 'Login',
                path: '/Login',
                component:Login
            },
            {
                name:'AppIndex',
                path:'index',
                component: AppIndex
            }
        ]
    }
)