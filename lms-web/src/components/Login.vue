<template>
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
            <el-button v-on:click="login">登录</el-button>
        </el-form>
        <div class="some-bt">
            <el-button v-on:click="jumpRegister" class="register-bt" type="text">立即注册</el-button>
        </div>
    </el-card>
</template>

<script>

    import constant from "../constant";

    export default {
        name: 'Login',
        data() {
            return {
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
                                        message: value,
                                        type: "success",
                                        position:constant.NOTIFY_POS,
                                    });
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
    .login-card {
        width: 350px;
        position: center;
        margin: 15% auto auto auto;
        box-shadow: 0 0 10px #cac6c6;
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
</style>