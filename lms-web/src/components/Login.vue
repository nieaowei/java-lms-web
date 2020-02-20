<template>

        <el-card class="login-card" shadow="never">
            <el-form :model="loginForm" class="login-container" ref="loginForm" status-icon="true" :rules="rules" label-width="65px" label-position="left">
                <h3>用户登录</h3>
                <el-form-item label="账号：" prop="username">
                    <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码：" prop="password">
                    <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                    <el-button v-on:click="login">登录</el-button>
            </el-form>
            <div class="some-bt">
                <el-button  v-on:click="jumpRegister" class="register-bt" type="text" >立即注册</el-button>
            </div>
        </el-card>

</template>

<script>

    export default {
        name: 'Login',
        data () {
            var checkUserName = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('账号不能为空'));
                }
                callback()
            };
            var checkPass = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('密码不能为空'));
                }
                callback()
            };
            return {
                loginForm: {
                    username: '',
                    password: ''
                },
                rules:{
                    username:[
                        {validator: checkUserName, trigger: 'blur'}
                  ],
                    password:[
                        {validator:checkPass,trigger:'blur'}
                    ],
                },
                responseResult: []
            }
        },
        methods: {
            login () {
                // this.$refs['loginForm'].validate((valid) => {
                //     if (valid) {
                //         return false;
                //     }
                // });
                this.$refs['loginForm'].validate((valid) =>{
                        if (!valid) {
                            return false;
                        }
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
                );

            },
            jumpRegister(){
                this.$router.push({name:'Register'})
            }
        }
    }
</script>

<style>
    .login-card{
        width: 350px;
        position: center;
        margin: 15% auto auto auto;
        box-shadow: 0 0 10px #cac6c6;
    }
    .login-card:hover{
        box-shadow: 0 0 25px #cac6c6;
    }
    .some-bt{
        alignment: left;
        text-align: right;
        margin-top: 5%;
        margin-right: 10%;
    }
</style>