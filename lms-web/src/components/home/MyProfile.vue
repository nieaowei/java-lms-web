<template>
    <el-tabs tab-position="right" :stretch="true" type="border-card">
        <el-tab-pane label="基本信息">
            <el-divider></el-divider>
            <span>用户ID：{{this.$store.state.MyProfile.profile['uiid']}}</span>
            <el-divider></el-divider>

            <span>用户名：{{this.$store.state.MyProfile.profile['username']}}</span>
            <el-divider></el-divider>

            <span>手机号码：{{this.$store.state.MyProfile.profile['phonenum']}}</span>
            <el-divider></el-divider>

            <span>注册时间：{{this.$store.state.MyProfile.profile['createtime']}}</span>
            <el-divider></el-divider>

            <span>上一次修改信息时间：{{this.$store.state.MyProfile.profile['updatetime']}}</span>
            <el-divider></el-divider>
        </el-tab-pane>
        <el-tab-pane label="修改密码">
            <!--            <el-card>-->
            <el-steps :active="active" finish-status="success" align-center style="margin-top: 100px">
                    <el-step title="验证旧密码">
                    </el-step>
                    <el-step title="输入新密码">
                    </el-step>
                    <el-step title="修改成功">
                    </el-step>
            </el-steps>
            <!--            </el-card>-->
            <div>
                <el-input v-if="active===0" type="text" label="旧密码：" placeholder="请输入旧密码"
                style="padding: 10%"></el-input>
                <el-input v-else-if="active===1" type="text" label="新密码：" placeholder="请输入新密码"
                          style="padding: 10%"></el-input>
                <span v-else >修改成功</span>
            </div>
            <el-button v-if="active<2" style="margin-bottom: 12px;" @click="nextStep">下一步</el-button>
            <el-button v-else style="margin-bottom: 12px;" @click="nextStep">完成</el-button>
        </el-tab-pane>

    </el-tabs>

</template>

<script>
    export default {
        name: "MyProfile",
        data() {
            var checkTwoPass = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('请再次输入密码'))
                } else if (value !== this.registerForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                active: 0,
                registerForm: {
                    username: '',
                    password: '',
                    checkPass: '',
                    phonenum: ''
                },
                rules: {
                    checkPass: [
                        {validator: checkTwoPass, trigger: 'blur', required: true}
                    ],
                },
                responseResult: []
            }
        },
        methods: {
            nextStep() {
                if (this.active++ > 2) {
                    this.active = 0;
                }
            }
        }
    }
</script>

<style scoped>
    .el-input {
        min-width: 200px;
        width: 20%;
    }

    .profile {
        width: 400px;
        margin: 25%;
    }

    .el-tab-pane{
        min-height: 520px;
    }
</style>