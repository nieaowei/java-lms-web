<template>

    <div>
        <div style="z-index: -2;position: absolute;top: 0;width: 99%; height: 99%;">
            <el-image
                    style="width: 100%; height: 100%;"
                    src="/login.jpg"
                    fit="fit"></el-image>
        </div>
        <div  class="logo"   style="z-index: -1;position: absolute;width: 99%;top: 10%">
            <el-image
                    style="width: 100%; height: 100%;"
                    src="/logo.png"
                    fit="cover"></el-image>
        </div>

<!--        <div class="background">-->
<!--            <img src="https://ae01.alicdn.com/kf/H6c1654253dc143d9b862a789d6d6a97dO.jpg" width="100%" height="100%" alt="" />-->
<!--        </div>-->

        <el-card v-loading="this.$store.state.Login.loading" class="login-card" shadow="never">
            <el-form :model="loginForm" class="login-container" ref="loginForm" :status-icon="true" :rules="rules"
                     label-width="65px" label-position="left">
                <h3>用户登录</h3>
                <el-form-item label="账号：" prop="username">
                    <el-input type="text" autofocus v-model="loginForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码：" prop="password">
                    <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <el-form-item label="类型：">
                    <el-select v-model="loginType" placeholder="请选择" >
                        <el-option
                                v-for="item in options"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-button v-on:click="login">登录</el-button>
            </el-form>
            <div class="some-bt">
                <el-tooltip class="item" content="请联系管理员为您重置密码" placement="bottom" effect="dark">
                    <el-button type="text">忘记密码</el-button>
                </el-tooltip>
                <el-button v-on:click="jumpRegister" class="register-bt" type="text">立即注册</el-button>
            </div>
        </el-card>

    </div>

</template>

<script>

    import constant from "../constant";

    export default {
        name: 'Login',
        data() {
            return {
                loginType:false,
                options:[
                    {
                        value:false,
                        label:'普通用户',
                    },
                    {
                        value:true,
                        label:'管理员',
                    }
                ],
                loginForm: {
                    username: '',
                    password: ''
                },
                rules: {
                    username: [
                        {validator: this.$store.state.Login.checkUserName, trigger: 'blur'}
                    ],
                    password: [
                        {validator: this.$store.state.Login.checkPass, trigger: 'blur'}
                    ],
                },
                responseResult: []
            }
        },
        methods: {
            login() {
                var flag1 = false
                this.$refs['loginForm'].validate((valid) => {
                        flag1 = valid;
                    }
                );
                if (flag1) {
                    this.$store.dispatch('Login/login', this.loginForm)
                        .then(
                            (value) => {
                                    this.$notify({
                                        message: value.msg,
                                        type: "success",
                                        position:constant.NOTIFY_POS,
                                    });
                                    this.$store.commit("AppIndex/saveCurUserName",value.data)
                                    this.$router.push({name: 'AppIndex'})
                            },
                            (err) => {
                                this.$notify({
                                    message: err,
                                    type: "error",
                                    position:constant.NOTIFY_POS,
                                });
                            }
                        )

                }

            },
            jumpRegister() {
                this.$router.push({name: 'Register'})
            }
        }
    }
</script>

<style>
    .background{
        /*width:100%;*/
        /*height:100%;  !**宽高100%是为了图片铺满屏幕 *!*/
        z-index:-1;
        position: absolute;
        /*background: center top;*/
    }


    .login-card {
        width: 350px;
        /*position: center;*/
        margin: 15% auto auto auto;
        /*padding: 15% auto auto auto;*/
        /*padding-top: 15%;*/
        box-shadow: 0 0 10px #cac6c6;
        background: transparent;

    }
    .el-form{
        background: transparent;

    }

    .login-card:hover {
        box-shadow: 0 0 25px #cac6c6;
    }

    .some-bt {
        alignment: left;
        text-align: right;
        margin-top: 5%;
        margin-right: 10%;
    }
    .logo{
    }
</style>
