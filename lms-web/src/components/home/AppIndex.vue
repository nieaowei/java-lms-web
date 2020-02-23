<template>
    <div class="main-container">
        <el-container  style="height: 100%" class="outside-container" >
            <el-header height="80px">xxxx</el-header>
            <el-container>
                <el-aside>
                        <el-card class="outside-card">
                            <el-card class="inside-card">
                                <el-avatar :size="100"
                                           fit="fill"
                                           src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png"></el-avatar>
                                <div style="padding: 0px;">
                                    <div class="bottom clearfix">
                                        刘军民
                                    </div>
                                </div>
                            </el-card>
                            
                            <el-menu
                                    default-active="1"
                                    @select="handleSelect">
                                <el-menu-item index="1" >
<!--                                    <template slot="title">-->
                                        <i class="el-icon-menu"></i>
                                        <span>我的课程</span>
<!--                                    </template>-->
                                </el-menu-item>
                                <el-menu-item index="2">
                                    <i class="el-icon-user"></i>
                                    <span>个人资料</span>
                                </el-menu-item>
                                <el-menu-item index="3">
                                    <i class="el-icon-document-copy"></i>
                                    <span >文档课程</span>
                                </el-menu-item>
                                <el-menu-item index="4">
                                    <i class="el-icon-video-camera"></i>
                                    <span>视频课程</span>
                                </el-menu-item>
                                <el-menu-item index="5">
                                    <i class="el-icon-edit-outline"></i>
                                    <span>在线考核</span>
                                </el-menu-item>
                                <el-menu-item index="6">
                                    <i class="el-icon-document"></i>
                                    <span >退出</span>
                                </el-menu-item>
                            </el-menu>
                        </el-card>
                </el-aside>
                <el-main>
                    <keep-alive>
                        <component v-bind:is="currentView"></component>
                    </keep-alive>
                </el-main>
            </el-container>
            <el-footer>
            </el-footer>
        </el-container>
    </div>
</template>

<script>
    import MyClass from "./MyClass";
    import MyProfile from "./MyProfile";
    export default {
        name: "AppIndex",
        components: {MyProfile, MyClass},
        data(){
            return{
                currentView: MyProfile,
                menuViews: [MyClass,MyProfile]
            }
        },
        methods:{
            handleSelect(key,keyPath) {
                //todo 处理菜单响应
                console.log('菜单响应');
                console.log(keyPath)
                this.currentView=this.menuViews[parseInt(key,10)-1];
            },
        }

    }
</script>

<style scoped>
    /*.transition-box {*/
    /*    margin-bottom: 10px;*/
    /*    width: 200px;*/
    /*    height: 100px;*/
    /*    border-radius: 4px;*/
    /*    background-color: #409EFF;*/
    /*    text-align: center;*/
    /*    color: #fff;*/
    /*    padding: 40px 20px;*/
    /*    box-sizing: border-box;*/
    /*    margin-right: 20px;*/
    /*}*/

    .el-header{
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
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        /*line-height: 200px;*/
        /*min-width: 100px;*/
        /*width: 20%;*/
    }

    .el-main {
        /*background-color: #E9EEF3;*/
        /*color: #333;*/
        text-align: center;
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
    .outside-card{
        box-shadow: 0 0 5px #cac6c6;
        margin: 10px;
    }

    .inside-card{

    }
    .el-card{
        box-shadow: 0 0 5px #cac6c6;
    }
    .el-card:hover{
        box-shadow: 0 0 30px #cac6c6;
    }

</style>