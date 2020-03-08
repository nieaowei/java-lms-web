<template>
    <el-tabs  type="border-card"  :stretch="true">
        <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i>文档课程</span>
                <el-row :gutter="10">
                    <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                            v-for="classe in this.$store.state.DocClass.docList" :key="classe.id">
                        <el-card :body-style="{ padding: '0px' }">
                            <el-image
                                    style="width: 100%; height: 150px"
                                    :src="classe.cover"
                                    fit="cover"></el-image>
                            <div style="padding: 14px;">
                                <span>{{classe.name}}({{classe.vlid}})</span>
                                <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                <div class="bottom clearfix">
                                    <span class="time">上传人: {{classe.username}}</span><br/>
                                    <span class="time">学习时长: {{(classe.duration/60).toFixed(2)}}分钟</span><br/>
                                    <time class="time">创建时间: {{classe.createtime}}</time><br/>

                                </div>
                                <el-button v-if="classe.flag"
                                           icon="el-icon-circle-check"
                                           type="success" class="button">已添加
                                </el-button>
                                <el-button v-else icon="el-icon-circle-plus-outline" type="primary" class="button" v-on:click="addDocLearn(classe.vlid)">添加学习</el-button>
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
        computed: {

        },
        methods:{
            addDocLearn(vlid){
                console.log(this.$store.state.DocClass.docList)
                this.$store.dispatch("DocClass/addDocLearn",vlid).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve,
                            position: constant.NOTIFY_POS,
                        })
                        console.log(this.$store.state.DocClass.docList)

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
                    ()=>{
                        console.log(this.$store.state.DocClass.docList)
                    }
                )
            }
        },
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
    .clearfix{
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
</style>