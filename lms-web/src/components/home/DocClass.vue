<template>
    <el-tabs type="border-card" :stretch="true">
        <el-tab-pane>
            <span slot="label"><i class="el-icon-date"></i>文档课程</span>
            <el-row :gutter="10">
                <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                        v-for="(classe,key) in this.$store.state.DocClass.docList" :key="key">
                    <el-card :body-style="{ padding: '0px' }">
                        <el-image
                                style="width: 100%; height: 150px"
                                :src="classe.cover"
                                fit="cover"></el-image>
                        <div style="padding: 14px;">
                            <span>{{classe.name}}({{classe.dlid}})</span>
                            <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                            <div class="bottom clearfix">
                                <span class="time">上传人: {{classe.username}}</span><br/>
                                <span class="time">学习时长: {{(classe.duration/60).toFixed(2)}}分钟</span><br/>
                                <time class="time">创建时间: {{classe.createtime.split(' ')[0]}}</time>
                                <br/>
                            </div>
                            <el-button v-if="classe.flag"
                                       icon="el-icon-circle-check"
                                       type="success" class="button">已添加
                            </el-button>
                            <el-button v-else icon="el-icon-circle-plus-outline" type="primary" class="button"
                                       v-on:click="addDocLearn(key)">添加学习
                            </el-button>
                            <div>
                                <span class="time" >学习人数: {{classe.count}} 人</span>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-tab-pane>
    </el-tabs>

</template>

<script>
    // eslint-disable-next-line no-unused-vars
    import constant from "../../constant";

    export default {
        name: "DocClass",
        computed: {},
        methods: {
            addDocLearn(key) {
                this.$store.dispatch("DocClass/addDocLearn", key).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('MyClass/addMyDoc', resolve.data);
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
                    }
                )
            }
        },
        created() {
            this.$store.commit('AppIndex/changeLoading', true);
            this.$store.dispatch('DocClass/getDocList').then(
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

    .el-tabs{
        background: transparent;
    }
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
        height: 370px;
        background: azure;

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
