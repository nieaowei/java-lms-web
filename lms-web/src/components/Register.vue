<template>

    <el-card class="register-card" shadow="never">
        <el-form :model="registerForm" ref="registerForm" status-icon="true" :rules="rules" class="register-container" label-width="100px" label-position="left">
            <h3>用户注册</h3>
            <el-form-item label="账号：" prop="username" :rules="[
                            { required: true, message: '账号不能为空'}]">
                <el-input type="text" v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" :rules="[
                            { required: true, message: '密码不能为空'}]">
                <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="确认密码：" prop="checkPass" >
                <el-input type="password" v-model="registerForm.checkPass" placeholder="请再次输入密码"></el-input>
            </el-form-item>
            <el-form-item label="手机号码：" prop="phonenum" :rules="[
                            { required: true, message: '手机号码不能为空'},
                            {type:'number', message: '手机号码格式不正确'}]">
                <el-input type="text" v-model.number="registerForm.phonenum" placeholder="请输入手机号码"></el-input>
            </el-form-item>
            <el-button v-on:click="register">注册</el-button>
        </el-form>
        <div class="some-bt">
            <el-button type="text" v-on:click="jumpLogin" >立即登录</el-button>
        </div>
    </el-card>

</template>

<script>

    export default {
        name: "Register",
        data(){
            var checkTwoPass = (rule, value, callback) => {
                if (!value){
                    callback(new Error('请再次输入密码'))
                }else if (value !== this.registerForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                registerForm: {
                    username: '',
                    password: '',
                    checkPass: '',
                    phonenum: ''
                },
                rules: {
                        checkPass:[
                            { validator:checkTwoPass,trigger:'blur',required:true}
                        ],
                },
                responseResult: []
            }
        },
        methods:{
            register(){
                this.$refs['registerForm'].validate((valid) => {
                    if (valid) {
                        console.log("ok")
                        this.$axios
                            .post('/register', {
                                username: this.registerForm.username,
                                password: this.registerForm.password,
                                phonenum: this.registerForm.phonenum
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
                });

            },
            jumpLogin() {
                this.$router.push({path:'/login'});
                console.log("跳转")
            }
        }
    }
</script>

<style scoped>
    .register-card{
        width: 350px;
        position: center;
        margin: 10% auto auto auto;
        box-shadow: 0 0 10px #cac6c6;
    }
    .register-card:hover{
        box-shadow: 0 0 25px #cac6c6;
    }
    .some-bt{
        alignment: left;
        text-align: right;
        margin-top: 5%;
        margin-right: 10%;
    }
</style>