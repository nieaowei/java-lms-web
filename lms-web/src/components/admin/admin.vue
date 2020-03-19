<template>
    <el-tabs tab-position="right" type="border-card">
        <el-tab-pane label="文档管理">
            <component v-bind:is="adminDoc"></component>
        </el-tab-pane>
        <el-tab-pane label="视频管理">
            <component v-bind:is="adminVideo"></component>
        </el-tab-pane>
        <el-tab-pane label="考核管理">
            <component v-bind:is="adminTest"></component>
        </el-tab-pane>
    </el-tabs>
</template>

<script>
    import adminDoc from "./adminDoc";
    import constant from "../../constant";
    import adminVideo from "./adminVideo";
    import adminTest from "./adminTest";

    export default {
        name: 'admin',
        data() {
            return {
                adminDoc: adminDoc,
                adminVideo: adminVideo,
                adminTest: adminTest,
                dynamicValidateForm: {
                    domains: [{
                        value: ''
                    }],
                    email: ''
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        alert('submit!');
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            removeDomain(item) {
                var index = this.dynamicValidateForm.domains.indexOf(item)
                if (index !== -1) {
                    this.dynamicValidateForm.domains.splice(index, 1)
                }
            },
            addDomain() {
                this.dynamicValidateForm.domains.push({
                    value: '',
                    key: Date.now()
                });
            }
        },
        created() {
            this.$store.dispatch('adminDoc/getDocList').then(
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
            )
        }
    }
</script>

<style>
    .el-tab-pane {
        min-height: 520px;
    }
</style>