<template>
    <el-card>
            <el-form label-width="100px">
                <el-form-item label="账号ID："  prop="username" :rules="[
                            { required: true, message: '账号不能为空'}]">
                    <el-input type="text" disabled="true" v-model="registerForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="用户名：" prop="password" :rules="[
                            { required: true, message: '密码不能为空'}]">
                    <el-input type="text" :disabled="true" v-model="registerForm.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="手机号码：" prop="phonenum" :rules="[
                            { required: true, message: '手机号码不能为空'},
                            {type:'number', message: '手机号码格式不正确'}]">
                    <el-input type="text" :disabled="true" v-model.number="registerForm.phonenum" placeholder="请输入手机号码"></el-input>
                </el-form-item>
            </el-form>
    </el-card>

</template>

<script>
    export default {
        name: "MyProfile",
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
    }
</script>

<style scoped>
    .el-card{
        box-shadow: 0 0 5px #cac6c6;
        /*padding: 10%;*/
        /*margin: 20px;*/
    }
    .el-input{
        min-width: 200px;
        width: 20%;
    }
    .el-card:hover{
        box-shadow: 0 0 30px #cac6c6;
    }
    .profile{
        width: 400px;
        margin: 25%;
    }
    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }
</style>