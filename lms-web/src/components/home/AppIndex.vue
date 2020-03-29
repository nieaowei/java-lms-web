<template>
    <div class="main-container">
        <el-container style="height: 100%" class="outside-container">
            <el-header height="80px">学习管理系统</el-header>
            <!--            <div class="midille-container">-->

            <el-container class="midille-container">
                <el-container>
                    <el-aside>
                        <!--                        <el-card class="outside-card">-->
                        <el-card class="inside-card">
                            <el-avatar :size="100"
                                       fit="fill"
                                       src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"></el-avatar>
                            <div style="padding: 0px;">
                                <div class="bottom clearfix">
                                    {{this.$store.state.MyProfile.profile['username']}}
                                </div>
                            </div>
                        </el-card>

                        <el-menu
                                :default-active="menuActive"
                                @select="handleSelect">
                            <el-menu-item index="1" @click="test=false" v-if="this.$store.state.MyProfile.profile['admin']===false">
                                <!--                                    <template slot="title">-->
                                <i class="el-icon-menu"></i>
                                <span>我的课程</span>
                                <!--                                    </template>-->
                            </el-menu-item>
                            <el-menu-item index="2">
                                <i class="el-icon-user"></i>
                                <span>个人资料</span>
                            </el-menu-item>
                            <el-menu-item index="3" v-if="this.$store.state.MyProfile.profile['admin']===false">
                                <i class="el-icon-document-copy"></i>
                                <span>文档课程</span>
                            </el-menu-item>
                            <el-menu-item index="4" v-if="this.$store.state.MyProfile.profile['admin']===false">
                                <i class="el-icon-video-camera"></i>
                                <span>视频课程</span>
                            </el-menu-item>
                            <el-menu-item index="5" v-if="this.$store.state.MyProfile.profile['admin']===false">
                                <i class="el-icon-edit-outline"></i>
                                <span>在线考核</span>
                            </el-menu-item>
                            <el-menu-item index="6" v-if="this.$store.state.MyProfile.profile['admin']===true">
                                <i class="el-icon-document-copy"></i>
                                <span>课程管理</span>
                            </el-menu-item>
                            <el-menu-item index="7" v-if="this.$store.state.MyProfile.profile['admin']===true">
                                <i class="el-icon-c-scale-to-original"></i>
                                <span>用户管理</span>
                            </el-menu-item>
                            <el-menu-item index="8">
                                <i class="el-icon-back"></i>
                                <span>退出</span>
                            </el-menu-item>
                        </el-menu>
                        <!--                        </el-card>-->
                    </el-aside>
                    <el-main v-loading=this.$store.state.AppIndex.loading>
                        <keep-alive>
                            <component v-bind:is="this.$store.state.AppIndex.currentView"></component>
                        </keep-alive>
                    </el-main>
                </el-container>
                <el-footer>
                </el-footer>
            </el-container>

        </el-container>
        <div id="entire-bottom" v-show="this.$store.state.AppIndex.bottom.enable">
            <el-progress v-if="this.$store.state.AppIndex.bottom.percent===100" status="success" :text-inside="true" :stroke-width="20" :percentage="100"></el-progress>
            <el-progress v-else="" :text-inside="true" :stroke-width="20" :percentage="this.$store.state.AppIndex.bottom.percent"></el-progress>
        </div>
    </div>
</template>

<script>
    import MyClass from "./MyClass";
    import admin from "../admin/admin";

    export default {
        name: "AppIndex",
        data() {
            return {
                menuActive:'-1',
            }
        },
        methods: {
            // eslint-disable-next-line no-unused-vars
            handleSelect(key, keyPath) {
                if (key==='8'){
                    var keys = document.cookie.match(/[^ =;]+(?==)/g)
                    if (keys) {
                        for (var i = keys.length; i--;) {
                            document.cookie = keys[i] + '=0;path=/;expires=' + new Date(0).toUTCString() // 清除当前域名下的,例如：m.ratingdog.cn
                            document.cookie = keys[i] + '=0;path=/;domain=' + document.domain + ';expires=' + new Date(0).toUTCString() // 清除当前域名下的，例如 .m.ratingdog.cn
                            document.cookie = keys[i] + '=0;path=/;domain=;expires=' + new Date(0).toUTCString() // 清除一级域名下的或指定的，例如 .ratingdog.cn
                        }
                        this.$router.push('login')
                    }
                }else {
                    this.$store.commit('AppIndex/changeCurrentView', this.$store.state.AppIndex.menuViews[parseInt(key, 10) - 1])
                }
            },
        },
        created() {
            this.$store.dispatch('MyProfile/getProfile').then(
                ()=>{
                    if (this.$store.state.MyProfile.profile['admin']===true){
                        console.log('jump admin')
                        this.$store.commit('AppIndex/changeCurrentView',admin)
                    }else{
                        console.log('jump user')
                        this.$store.commit('AppIndex/changeCurrentView',MyClass)
                    }
                }
            )
        }

    }
</script>

<style scoped>

    .el-header {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;
        /*height: 300px;*/
    }

    .el-footer {
        background-color: #B3C0D1;
        color: #333;
        text-align: center;
        line-height: 60px;

    }

    .el-aside {
        /*background-color: #D3DCE6;*/
        color: #333;
        text-align: center;
        margin-top: 10px;
        border: #dcdfe6 solid 1px;
    }

    .el-main {
        /*background-color: #E9EEF3;*/
        /*color: #333;*/
        text-align: center;
        padding: 10px;
        /*line-height: 160px;*/
    }

    body > .el-container {
        margin-bottom: 10px;
        width: 100%;
        height: 100%;
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .outside-card {
        box-shadow: 0 0 5px #cac6c6;
        margin: 10px;
    }


    .midille-container {
        /*margin: 50px;*/
        padding-left: 5%;
        padding-right: 5%;
    }

    #entire-bottom{
        left: 0;
        position: fixed;
        bottom: 0;
        width: 100%;
        z-index: 9999;
    }


</style>
