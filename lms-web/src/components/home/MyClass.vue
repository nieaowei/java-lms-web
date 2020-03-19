<template>
    <!--    <el-card body-style="padding:0px">-->
    <el-tabs @tab-click="handleClick" type="border-card" tab-position="right" :stretch="true">
        <el-tab-pane>
            <span slot="label">学习文档</span>
            <el-row :gutter="10">
                <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                        v-for="classe in this.$store.state.MyClass.myDocs" :key="classe.id">
                    <el-card class="data-card" :body-style="{ padding: '0px' }">
                        <el-image
                                style="width: 100%; height: 150px"
                                :src="classe.cover"
                                fit="cover"></el-image>
                        <div style="padding: 14px;">
                            <span>{{classe.name}}({{classe.dlid}})</span>
                            <el-progress v-if="classe.finished" :percentage="100" status="success"></el-progress>
                            <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                            <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                            <div class="bottom clearfix">
                                <time class="time"></time>
                                <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                           class="button">已完成
                                </el-button>
                                <el-button v-else icon="el-icon-right" type="primary" class="button"
                                           @click="openDocStudy(classe.name,classe)">继续学习
                                </el-button>
                            </div>
                        </div>
                    </el-card>

                </el-col>
            </el-row>
        </el-tab-pane>

        <el-tab-pane label="学习视频">
            <el-row :gutter="10">
                <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                        v-for="classe in this.$store.state.MyClass.myVideos" :key="classe.id">
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
                                <el-button v-else icon="el-icon-right" type="primary" class="button"
                                           @click="openVideoStudy(classe.name,classe)">继续学习
                                </el-button>
                            </div>
                        </div>
                    </el-card>

                </el-col>
            </el-row>
        </el-tab-pane>
        <el-tab-pane label="已完成考核">
            <el-row :gutter="10">
                <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                        v-for="classe in this.$store.state.MyClass.myTests" :key="classe.id">
                    <el-card class="data-card" :body-style="{ padding: '0px' }">
                        <el-image
                                style="width: 100%; height: 150px"
                                :src="classe.cover"
                                fit="cover"></el-image>
                        <div style="padding: 14px;">
                            <span>{{classe.title}}({{classe.tiid}})</span>
                            <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                            <div class="bottom clearfix">
                                <el-tag class="time">完成时间：{{classe.createtime.split(' ')[0]}}</el-tag><br/>
<!--                                <el-button icon="el-icon-circle-check" type="success"-->
<!--                                           class="button">{{classe.grade}}-->
<!--                                </el-button>-->
                                <el-tag>总 分：{{classe.sum}}</el-tag><br/>
                                <el-tag type="success">得 分：{{classe.grade}}</el-tag><br/>

                            </div>
                        </div>
                    </el-card>

                </el-col>
            </el-row>
        </el-tab-pane>

        <el-dialog v-loading="loading"
                   style="margin-top: 0;"
                   :title="studyWindow.title"
                   :visible.sync="studyWindowEnable"
                   width="80%"
                   @open="$store.commit('AppIndex/setBottomEnable',true)"
                   @close="$store.commit('AppIndex/setBottomEnable',false)"
                   center>
            <component v-bind:is="this.studyWindow.instance"></component>
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
    import studyVidoe from "../study/studyVidoe";

    export default {
        name: "MyClass",
        // eslint-disable-next-line vue/no-unused-components
        components: {studyDoc},
        data() {
            return {
                loading: false,
                studyWindowEnable: false,
                studyWindow: {
                    timer: null,
                    instance: null,
                    enable: false,
                    currentClass: '',
                    title: '',
                }
            }
        },
        watch: {
            studyWindowEnable: {
                handler(newVal) {
                    // ...
                    console.log(newVal)

                    if (newVal === true) {
                        //todo 启动定时上传
                        console.log("start Timer")
                        if (this.$store.state.AppIndex.bottom.percent !== 100) {

                            if (this.studyWindow.instance===studyDoc){
                                this.studyWindow.timer = setInterval(() => {
                                    console.log("doc exec timer")
                                    this.$store.dispatch("MyClass/updateDuration", this.studyWindow.currentClass.dlid).then(
                                        // eslint-disable-next-line no-unused-vars
                                        (resolve) => {
                                            console.log("update",resolve)
                                            this.$store.commit("AppIndex/setBottomPercent", resolve.percent)
                                        },
                                        // eslint-disable-next-line no-unused-vars
                                        (reject) => {
                                            console.log("update",reject)

                                        }
                                    )
                                    if (this.$store.state.AppIndex.bottom.percent === 100) {
                                        clearInterval(this.studyWindow.timer)
                                    }
                                }, 10500)
                            }else if (this.studyWindow.instance===studyVidoe){
                                this.studyWindow.timer = setInterval(() => {
                                    console.log("exec timer")
                                    this.$store.dispatch("MyClass/updateVideoDuration", this.studyWindow.currentClass).then(
                                        // eslint-disable-next-line no-unused-vars
                                        (resolve) => {
                                            console.log(resolve)
                                            this.$store.commit("AppIndex/setBottomPercent", resolve.percent)
                                        },
                                        // eslint-disable-next-line no-unused-vars
                                        (reject) => {

                                        }
                                    )
                                    if (this.$store.state.AppIndex.bottom.percent === 100) {
                                        clearInterval(this.studyWindow.timer)
                                    }
                                }, 10500)
                            }

                        }
                        console.log(newVal)
                    }
                    if (newVal === false) {
                        console.log("clear timer")
                        clearInterval(this.studyWindow.timer)
                        this.studyWindow.instance=null;
                    }
                },
                deep: true,
            }
        },
        methods: {
            openDocStudy(title, classe) {
                console.log(classe)
                this.studyWindow.instance = studyDoc;
                this.studyWindowEnable = true
                this.studyWindow.title = title;
                this.loading = true;
                this.studyWindow.currentClass = classe
                this.$store.dispatch("studyDoc/loadPdfData", classe.dlid).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve,
                            position: constant.NOTIFY_POS,
                        })
                        // this.$store.commit("AppIndex/setBottomEnable", true);
                        this.$store.commit("AppIndex/setBottomPercent", Number(classe.percent.toFixed(1)));

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
                        this.loading = false;
                    }
                )
            },
            openVideoStudy(title, classe) {
                console.log(classe)
                this.studyWindow.instance = studyVidoe;
                this.studyWindowEnable = true
                this.studyWindow["title"] = title;
                this.loading = true;
                this.studyWindow.currentClass = classe.vlid
                this.$store.dispatch("studyVideo/loadVideoData", classe.vlid).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve,
                            position: constant.NOTIFY_POS,
                        })
                        // this.$store.commit("AppIndex/setBottomEnable", true);
                        this.$store.commit("AppIndex/setBottomPercent", Number(classe.percent.toFixed(1)));

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
                        this.loading = false;
                    }
                )
            },

            // eslint-disable-next-line no-unused-vars
            handleClick(tab, event) {
                // console.log(tab, event);
            //     if (tab.index === '1') {
            //         console.log("get video")
            //         this.$store.dispatch('MyClass/getMyVideos')
            //             .then(
            //                 (value) => {
            //                     this.$notify({
            //                         message: value,
            //                         type: "success",
            //                         position: constant.NOTIFY_POS,
            //                     });
            //                 },
            //                 (err) => {
            //                     this.$notify({
            //                         message: err,
            //                         type: "error",
            //                         position: constant.NOTIFY_POS,
            //                     });
            //                 }
            //             )
            //     }else if (tab.index === '2'){
            //         console.log('get test')
            //     }
            }
        },
        created() {
            this.$store.commit('AppIndex/changeLoading', true);
            this.$store.dispatch('MyClass/getMyDocs').then(
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
            this.$store.dispatch('MyClass/getMyVideos')
                .then(
                    (value) => {
                        this.$notify({
                            message: value,
                            type: "success",
                            position: constant.NOTIFY_POS,
                        });
                    },
                    (err) => {
                        this.$notify({
                            message: err,
                            type: "error",
                            position: constant.NOTIFY_POS,
                        });
                    }
                )
            this.$store.dispatch('MyClass/getMyTests')
                .then(
                    (value) => {
                        this.$notify({
                            message: value,
                            type: "success",
                            position: constant.NOTIFY_POS,
                        });
                    },
                    (err) => {
                        this.$notify({
                            message: err,
                            type: "error",
                            position: constant.NOTIFY_POS,
                        });
                    }
                )
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

    .el-dialog__body {
        align-content: center;
        text-align: center;
        alignment: center;
    }

    .el-tab-pane{
        min-height: 520px;
    }

</style>