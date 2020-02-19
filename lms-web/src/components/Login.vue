<template>
    <el-card shadow="hover">
        <el-form class="login-container" label-position="left">
            <h3>用户登录</h3>
            <el-form-item label="账号：">
                <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码：">
                <el-input type="password" v-model="loginForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button v-on:click="login">登录</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>

    export default {
        name: 'Login',
        data () {
            return {
                loginForm: {
                    username: '',
                    password: ''
                },
                responseResult: []
            }
        },
        methods: {
            login () {
                this.$axios
                    .post('/login', {
                        username: this.loginForm.username,
                        password: this.loginForm.password
                    })
                    .then(successResponse => {
                        if (successResponse.data.code === 200) {
                            this.$router.replace({path: '/index'})
                        }
                    })
                    .catch(failResponse => {
                        //todo  失败处理
                        console.log(failResponse.toString())
                    })
            }
        }
    }
</script>

<style>
    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
</style>