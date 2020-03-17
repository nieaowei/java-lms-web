import Vue from 'vue'
import Router from 'vue-router'

import AppIndex from "@/components/home/AppIndex";
import Login from "@/components/Login";
import Register from "@/components/Register";
import studyDoc from "../components/study/studyDoc";
import studyVidoe from "../components/study/studyVidoe";
import admin from "../components/admin/admin";
import upload from "../components/admin/upload";
import doTest from "../components/study/doTest";

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
            },
            {
                name:'studyVideo',
                path:'/studyVideo',
                component:studyVidoe
            },
            {
                name:'admin',
                path:'/admin',
                component:admin,
            },
            {
                name:'upload',
                path:'/upload',
                component:upload,
            },
            {
                name:'dotest',
                path:'/dotest',
                component: doTest
            }
        ]
    }
)