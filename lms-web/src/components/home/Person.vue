<template>
    <el-card>
        <el-form label-width="100px">
            <el-form-item label="账号：" prop="username" :rules="[
                            { required: true, message: '账号不能为空'}]">
                <el-input type="text" v-model="registerForm.username" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码：" prop="password" :rules="[
                            { required: true, message: '密码不能为空'}]">
                <el-input type="password" v-model="registerForm.password" placeholder="请输入密码"></el-input>
            </el-form-item>
            <el-form-item label="手机号码：" prop="phonenum" :rules="[
                            { required: true, message: '手机号码不能为空'},
                            {type:'number', message: '手机号码格式不正确'}]">
                <el-input type="text" v-model.number="registerForm.phonenum" placeholder="请输入手机号码"></el-input>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
    export default {
        name: "Person",
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

</style>