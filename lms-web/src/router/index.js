import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from "@/components/home/AppIndex";
import Login from "@/components/Login";
import Register from "@/components/Register";
import studyDoc from "../components/study/studyDoc";

Vue.use(Router)

export default new Router(
    {
        mode:'history',
        routes:[
            {
                name: 'Login',
                path: '/login',
                component:Login
            },
            {
                name:'AppIndex',
                path:'/index',
                component: AppIndex
            },
            {
                name:'Register',
                path:'/register',
                component:Register
            },
            {
                name:'studyDoc',
                path:'/studyDoc',
                component:studyDoc
            },
            {
                path:'/src/assets'
            }
        ]
    }
)