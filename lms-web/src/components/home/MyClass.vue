<template>
    <!--    <el-card body-style="padding:0px">-->
    <el-tabs type="border-card" tab-position="right" :stretch="true">
        <el-tab-pane>
            <span slot="label"><i class="el-icon-date"></i>学习文档</span>
            <el-row :gutter="10">
                <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                        v-for="classe in this.$store.state.MyClass.myDocs" :key="classe.id">
                        <el-card class="data-card" :body-style="{ padding: '0px' }">
                            <el-image
                                    style="width: 100%; height: 150px"
                                    :src="classe.cover"
                                    fit="cover"></el-image>
                            <div style="padding: 14px;">
                                <span>{{classe.name}}({{classe.vlid}})</span>
                                <el-progress v-if="classe.finished" :percentage="100" status="success"></el-progress>
                                <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                                <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                <div class="bottom clearfix">
                                    <time class="time"></time>
                                    <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                               class="button">已完成
                                    </el-button>
                                    <el-button v-else icon="el-icon-right" type="primary" class="button" @click="openStudy(classe.name,classe)" >继续学习</el-button>
                                </div>
                            </div>
                        </el-card>

                </el-col>
            </el-row>
        </el-tab-pane>

        <el-tab-pane label="学习视频">
            <el-row :gutter="10">
                <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1" v-for="fit in fits" :key="fit.id">
                    <el-card :body-style="{ padding: '0px' }">
                        <el-image
                                :src="url"
                                :fit="fit"></el-image>
                        <div style="padding: 14px;">
                            <span>属性课</span>
                            <el-progress :percentage="50"></el-progress>
                            <div class="bottom clearfix">
                                <!--                                    <time class="time">{{ currentDate }}</time>-->
                                <el-button type="primary" class="button">开始学习</el-button>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-tab-pane>
        <el-tab-pane label="已完成考核">
            <!--            <login></login>-->
        </el-tab-pane>

        <el-dialog v-loading="loading"
                style="margin-top: 0;"
                :title="studyWindow.title"
                :visible.sync="studyWindowEnable"
                width="95%"
                   @close="$store.commit('AppIndex/setBottomEnable',false)"
        center>
                <component v-bind:is="this.studyWindow.instance" ></component>
            <span slot="footer" class="dialog-footer">

            <el-button type="primary" v-on:click="studyWindowEnable=false">关 闭</el-button>
                    </span>
        </el-dialog>
    </el-tabs>

    <!--    </el-card>-->

</template>

<script>
    import studyDoc from "../study/studyDoc";
    import constant from "../../constant";
    export default {
        name: "MyClass",
        // eslint-disable-next-line vue/no-unused-components
        components:{studyDoc},
        data() {
            return {
                loading :false,
                studyWindowEnable:false,
                studyWindow:{
                    timer:null,
                    instance:studyDoc,
                    enable:false,
                    currentClass:'',
                    title:'',
                },
                fits: 'fill',
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
            }
        },
        watch:{
            studyWindowEnable: {
                handler(newVal) {
                    // ...
                    console.log(newVal)

                    if (newVal===true){
                        //todo 启动定时上传
                        console.log("start Timer")
                        if (this.$store.state.AppIndex.bottom.percent!==100){
                            this.studyWindow.timer = setInterval(()=>{
                                console.log("exec timer")
                                this.$store.dispatch("MyClass/updateDuration",this.studyWindow.currentClass).then(
                                    // eslint-disable-next-line no-unused-vars
                                    (resolve)=>{
                                        console.log(resolve)
                                        this.$store.commit("AppIndex/setBottomPercent",resolve.percent)
                                    },
                                    // eslint-disable-next-line no-unused-vars
                                    (reject)=>{

                                    }
                                )
                                if (this.$store.state.AppIndex.bottom.percent===100){
                                    clearInterval(this.studyWindow.timer)
                                }
                            },10500)
                        }
                        console.log(newVal)
                    }
                    if (newVal===false){
                        console.log("clear timer")
                        clearInterval(this.studyWindow.timer)
                    }
                },
                deep:true,
            }
        },
        methods:{
            openStudy(title,classe){
                console.log(classe)
                this.studyWindowEnable=true
                this.studyWindow["title"]=title;
                this.loading=true;
                this.studyWindow.currentClass=classe.vlid
                this.$store.dispatch("studyDoc/loadPdfData",classe.vlid).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit("AppIndex/setBottomEnable",true);
                        this.$store.commit("AppIndex/setBottomPercent",Number(classe.percent.toFixed(1)));

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
                        this.loading=false;
                    }
                )
            }
        }
    }
</script>

<style scoped>
    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .el-card {
        box-shadow: 0 0 5px #cac6c6;
        min-height: 300px;
    }

    .el-card:hover {
        box-shadow: 0 0 30px #cac6c6;
    }

    .el-card {
        margin-bottom: 20px;
    }

    .el-dialog__body{
        align-content: center;
        text-align: center;
        alignment: center;
    }

</style>