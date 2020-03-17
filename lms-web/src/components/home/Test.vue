<template>

    <el-tabs type="border-card" :stretch="true">
        <el-tab-pane>
            <span slot="label"><i class="el-icon-date"></i>试卷列表</span>
            <el-row :gutter="10">
                <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                        v-for="(classe,key) in this.$store.state.Test.testList" :key="key">
                    <el-card :body-style="{ padding: '0px' }">
                        <el-image
                                style="width: 100%; height: 150px"
                                :src="classe.cover"
                                fit="cover"></el-image>
                        <div style="padding: 14px;">
                            <span>{{classe.title}}({{classe.tiid}})</span>
                            <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                            <div class="bottom clearfix">
                                <span class="time">上传人: {{classe.username}}</span><br/>
                                <span class="time">题数: {{classe.num}} 题</span><br/>
                                <span class="time">总分: {{classe.sum}} 分</span><br/>
                                <time class="time">创建时间: {{classe.createtime}}</time>
                                <br/>
                            </div>
                            <el-button v-if="classe.flag"
                                       icon="el-icon-circle-check"
                                       type="success" class="button">已完成
                            </el-button>
                            <el-button v-else icon="el-icon-circle-plus-outline" type="primary" class="button"
                                       v-on:click="doTest(key)">开始答题
                            </el-button>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-tab-pane>
        <el-dialog v-loading="this.loading"
                   style="margin-top: 0;"
                   :title="studyWindow.title"
                   :visible.sync="studyWindowEnable"
                   width="60%"
                   center>
            <component v-bind:is="studyWindow.instance"></component>
            <span slot="footer" class="dialog-footer">
            <el-button type="primary" v-on:click="commit">提 交</el-button>

            <el-button type="primary" v-on:click="studyWindowEnable=false">关 闭</el-button>
                    </span>
        </el-dialog>
    </el-tabs>

</template>

<script>
    // eslint-disable-next-line no-unused-vars
    import constant from "../../constant";
    import doTest from "../study/doTest";

    export default {
        name: "Test",
        data() {
            return {

                loading: false,
                studyWindowEnable: false,
                studyWindow: {
                    instance: doTest,
                    enable: false,
                    currentClass: '',
                    title: '',
                }
            }
        },
        watch: {
            studyWindowEnable: {
                handler(newVal) {
                    if (newVal === false) {
                        this.$store.commit('doTest/saveTestData', {})
                    }
                }
            }
        },
        computed: {},
        methods: {
            commit() {
                console.log(this.$store.getters["doTest/filterTestData"])
                this.$store.dispatch('doTest/postTestData').then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('Test/changeTestFlag', {key: resolve.data.tiid, data: true})
                        this.$store.commit('MyClass/addMyTest', resolve.data);
                        this.studyWindowEnable = false
                        // console.log(resolve.data)
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
            },
            doTest(key) {
                this.studyWindowEnable = true
                this.studyWindow.title = this.$store.state.Test.testList[key].title
                this.$store.dispatch('doTest/getTestData', this.$store.state.Test.testList[key].tiid).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('doTest/saveTestData', resolve.data)
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
            },

        },
        created() {
            // this.$store.commit('doTest/saveTestData',[{question:'你好吗？',answer:-1,opa:'刘军民',opb:'蔡徐坤',opc:'鹿晗',opd:'王宝强'}])

            this.$store.commit('AppIndex/changeLoading', true);
            this.$store.dispatch('Test/getTestList').then(
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
    .time {
        font-size: 13px;
        color: #999;
        /*text-align: left;*/
    }

    /*.bottom {*/
    /*    margin-top: 13px;*/
    /*    line-height: 12px;*/
    /*}*/
    .el-card {
        box-shadow: 0 0 5px #cac6c6;
        height: 350px;
    }

    .el-card:hover {
        box-shadow: 0 0 30px #cac6c6;
    }

    .el-card {
        margin-bottom: 20px;
    }

    .button {
        padding: 10px;
        margin: 10px;
    }

    .image {
        width: 100%;
        display: block;
    }

    .clearfix {
        text-align: left;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .el-tab-pane {
        min-height: 520px;
    }
</style>