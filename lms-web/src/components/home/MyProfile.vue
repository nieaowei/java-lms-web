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

            <span v-if="this.$store.state.MyProfile.profile['admin']===true">用户类型：管理员</span>
            <span v-else>用户类型：普通用户</span>
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
            <div style="position: absolute;margin-left: 40%;margin-top: 10%">
<!--                <el-form :model="registerForm" :status-icon="true" ref="registerForm" :rules="rules">-->
<!--                    <el-form-item   prop="oldPd"-->
<!--                                  :rules="[{require:true,message: '密码不能为空'}]">-->
<!--                        <el-input type="text"   placeholder="请输入旧密码"-->
<!--                                  v-model="registerForm.oldPd"></el-input>-->
<!--                    </el-form-item>-->
<!--&lt;!&ndash;                    <div v-else-if="active===1">&ndash;&gt;-->
<!--                        <el-form-item  :rules="[{require:true,message: '密码不能为空'}]" prop="newPd">-->
<!--                            <el-input type="text"   placeholder="请输入8位字母+数字的新密码"-->
<!--                                      v-model="registerForm.newPd"></el-input>-->
<!--                        </el-form-item>-->

<!--                        <el-form-item  prop="checkNewPd">-->
<!--                            <el-input type="text"   placeholder="请再次输入密码"-->
<!--                                      v-model="registerForm.checkNewPd"></el-input>-->
<!--                        </el-form-item>-->

<!--&lt;!&ndash;                    </div>&ndash;&gt;-->

<!--&lt;!&ndash;                    <span v-else>修改成功</span>&ndash;&gt;-->
<!--                </el-form>-->
                <el-form :model="registerForm" ref="registerForm1"  :status-icon="true" class="register-container">
                    <el-form-item v-if="active===0" prop="oldPd" :rules="[
                            { required: true, message: '旧密码不能为空'}]">
                        <el-input type="password" v-model="registerForm.oldPd" placeholder="请输旧密码"></el-input>
                    </el-form-item>
                    <el-form ref="registerForm" :model="registerForm" v-else-if="active===1" :rules="rules" :status-icon="true">
                        <el-form-item   prop="newPd" :rules="[
                            { required: true, message: '新密码不能为空'}]">
                            <el-input type="password" show-password v-model="registerForm.newPd" placeholder="请输入8~16位英文+数字新密码"></el-input>
                        </el-form-item>
                        <el-form-item prop="checkNewPd" >
                            <el-input type="password" show-password v-model="registerForm.checkNewPd" placeholder="请再次输入新密码"></el-input>
                        </el-form-item>
                    </el-form>
                </el-form>
                <el-button v-if="active<2" style="margin-bottom: 12px;" @click="nextStep">下一步</el-button>
                <el-button v-else style="margin-bottom: 12px;" @click="nextStep">完成</el-button>
            </div>

        </el-tab-pane>

    </el-tabs>

</template>

<script>
    import constant from "../../constant";

    export default {
        name: "MyProfile",
        data() {
            var checkTwoPass = (rule, value, callback) => {
                if (!value) {
                    callback(new Error('请再次输入密码'))
                } else if (value !== this.registerForm.newPd) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                active: 0,
                // newPd: '',
                // oldPd: '',
                // checkNewPd:'',
                registerForm: {
                    newPd: '',
                    oldPd: '',
                    checkNewPd: '',
                },
                rules: {
                    checkNewPd: [
                        {validator: checkTwoPass, trigger: 'blur', required: true}
                    ],
                },
                responseResult: []
            }
        },
        methods: {
            nextStep() {
                if (this.active+1 > 2) {
                    this.active = 0;
                }else if(this.active === 0){
                    this.$refs['registerForm1'].validate(
                        (valid)=>{
                            if (valid){
                                this.active++
                            }
                        }
                    )
                } else if(this.active === 1) {
                    this.$refs['registerForm'].validate((valid) => {
                        if (valid) {
                            this.active++
                            this.$store.dispatch('MyProfile/changeMyPd', {newVal: this.registerForm.newPd, oldVal: this.registerForm.oldPd}).then(
                                (resolve) => {
                                    this.$notify({
                                        type: "success",
                                        message: resolve.msg,
                                        position: constant.NOTIFY_POS,
                                    })
                                    this.active = 3
                                },
                                (reject) => {
                                    if (reject === constant.REDIRECT_LOGIN) {
                                        this.$router.push('login')
                                    }
                                    this.$notify({
                                        type: "error",
                                        message: reject,
                                        position: constant.NOTIFY_POS,
                                    })
                                    this.active = 0
                                }
                            ).finally(
                                () => {
                                    this.registerForm.newPd = ''
                                    this.registerForm.oldPd = ''
                                }
                            )
                        }else{
                            return
                        }
                    })

                }
                console.log(this.active)

            }
        },
        created() {
            this.$store.commit('AppIndex/changeLoading', true);
            this.$store.dispatch('MyProfile/getProfile').then(
                (resolve) => {
                    this.$notify({
                        type: "success",
                        message: resolve,
                        position: constant.NOTIFY_POS,
                    })
                },
                (reject) => {
                    if (reject === constant.REDIRECT_LOGIN) {
                        this.$router.push('login')
                    }
                    this.$notify({
                        type: "error",
                        message: reject,
                        position: constant.NOTIFY_POS,
                    })
                }
            ).finally(
                () => {
                    this.$store.commit('AppIndex/changeLoading', false);
                }
            );
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
    .el-card{
        background: azure;

    }

    .el-tab-pane {
        min-height: 520px;
    }
    .el-tabs{
        background: transparent;
    }

    .is-right{
        background: transparent;

    }
</style>
