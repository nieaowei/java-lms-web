<template>
    <!--    <el-card body-style="padding:0px">-->
    <el-tabs @tab-click="handleClick" type="border-card" tab-position="right" :stretch="true">

        <el-tab-pane label="学习文档">
            <!--            <span slot="label">学习文档</span>-->
            <el-tabs stretch>
                <el-tab-pane label="全部">
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
                                    <el-progress v-if="classe.finished" :percentage="100"
                                                 status="success"></el-progress>
                                    <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                                    <!--                                                            <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                <span class="time">
                                    已学习时间：{{parseInt(classe.duration/60)}}分{{(classe.duration%60)}}秒
                                </span>
                                    </div>
                                    <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                               class="button">已完成
                                    </el-button>
                                    <el-button v-else icon="el-icon-right" type="primary" class="button"
                                               @click="openDocStudy(classe.name,classe)">继续学习
                                    </el-button>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="未完成">
                    <el-row :gutter="10">
                        <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                                v-for="classe in this.$store.state.MyClass.myDocs" :key="classe.id">
                            <el-card class="data-card" :body-style="{ padding: '0px' }" v-if="classe.percent!==100">
                                <el-image
                                        style="width: 100%; height: 150px"
                                        :src="classe.cover"
                                        fit="cover"></el-image>
                                <div style="padding: 14px;">
                                    <span>{{classe.name}}({{classe.dlid}})</span>
                                    <el-progress v-if="classe.finished" :percentage="100"
                                                 status="success"></el-progress>
                                    <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                                    <!--                                                            <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                <span class="time">
                                    已学习时间：{{parseInt(classe.duration/60)}}分{{(classe.duration%60)}}秒
                                </span>
                                    </div>
                                    <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                               class="button">已完成
                                    </el-button>
                                    <el-button v-else icon="el-icon-right" type="primary" class="button"
                                               @click="openDocStudy(classe.name,classe)">继续学习
                                    </el-button>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="已完成">
                    <el-row :gutter="10">
                        <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                                v-for="classe in this.$store.state.MyClass.myDocs" :key="classe.id">
                            <el-card class="data-card" :body-style="{ padding: '0px' }" v-if="classe.percent===100">
                                <el-image
                                        style="width: 100%; height: 150px"
                                        :src="classe.cover"
                                        fit="cover"></el-image>
                                <div style="padding: 14px;">
                                    <span>{{classe.name}}({{classe.dlid}})</span>
                                    <el-progress v-if="classe.finished" :percentage="100"
                                                 status="success"></el-progress>
                                    <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                                    <!--                                                            <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                <span class="time">
                                    已学习时间：{{parseInt(classe.duration/60)}}分{{(classe.duration%60)}}秒
                                </span>
                                    </div>
                                    <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                               class="button">已完成
                                    </el-button>
                                    <el-button v-else icon="el-icon-right" type="primary" class="button"
                                               @click="openDocStudy(classe.name,classe)">继续学习
                                    </el-button>
                                </div>
                            </el-card>
                        </el-col>
                    </el-row>
                </el-tab-pane>
            </el-tabs>

        </el-tab-pane>

        <el-tab-pane label="学习视频">
            <el-tabs stretch>
                <el-tab-pane label="全部">
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
                                    <el-progress v-if="classe.finished" :percentage="100"
                                                 status="success"></el-progress>
                                    <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                                    <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                <span class="time">
                                    已学习时间：{{parseInt(classe.duration/60)}}分{{(classe.duration%60)}}秒
                                </span>

                                    </div>
                                    <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                               class="button">已完成
                                    </el-button>
                                    <el-button v-else icon="el-icon-right" type="primary" class="button"
                                               @click="openVideoStudy(classe.name,classe)">继续学习
                                    </el-button>
                                </div>
                            </el-card>

                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="未完成">
                    <el-row :gutter="10">
                        <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                                v-for="classe in this.$store.state.MyClass.myVideos" :key="classe.id">
                            <el-card class="data-card" :body-style="{ padding: '0px' }" v-if="classe.percent!==100">
                                <el-image
                                        style="width: 100%; height: 150px"
                                        :src="classe.cover"
                                        fit="cover"></el-image>
                                <div style="padding: 14px;">
                                    <span>{{classe.name}}({{classe.vlid}})</span>
                                    <el-progress v-if="classe.finished" :percentage="100"
                                                 status="success"></el-progress>
                                    <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                                    <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                <span class="time">
                                    已学习时间：{{parseInt(classe.duration/60)}}分{{(classe.duration%60)}}秒
                                </span>

                                    </div>
                                    <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                               class="button">已完成
                                    </el-button>
                                    <el-button v-else icon="el-icon-right" type="primary" class="button"
                                               @click="openVideoStudy(classe.name,classe)">继续学习
                                    </el-button>
                                </div>
                            </el-card>

                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="已完成">
                    <el-row :gutter="10">
                        <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                                v-for="classe in this.$store.state.MyClass.myVideos" :key="classe.id">
                            <el-card class="data-card" :body-style="{ padding: '0px' }" v-if="classe.percent>=100">
                                <el-image
                                        style="width: 100%; height: 150px"
                                        :src="classe.cover"
                                        fit="cover"></el-image>
                                <div style="padding: 14px;">
                                    <span>{{classe.name}}({{classe.vlid}})</span>
                                    <el-progress v-if="classe.finished" :percentage="100"
                                                 status="success"></el-progress>
                                    <el-progress v-else :percentage="Number(classe.percent.toFixed(1))"></el-progress>
                                    <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                <span class="time">
                                    已学习时间：{{parseInt(classe.duration/60)}}分{{(classe.duration%60)}}秒
                                </span>

                                    </div>
                                    <el-button v-if="classe.finished" icon="el-icon-circle-check" type="success"
                                               class="button">已完成
                                    </el-button>
                                    <el-button v-else icon="el-icon-right" type="primary" class="button"
                                               @click="openVideoStudy(classe.name,classe)">继续学习
                                    </el-button>
                                </div>
                            </el-card>

                        </el-col>
                    </el-row>
                </el-tab-pane>
            </el-tabs>

        </el-tab-pane>
        <el-tab-pane label="已完成考核">
            <el-tabs stretch>
                <el-tab-pane label="全部">
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
                                        <el-tag class="time">完成时间：{{classe.updatetime.split(' ')[0]}}</el-tag>
                                        <br/>
                                        <!--                                <el-button icon="el-icon-circle-check" type="success"-->
                                        <!--                                           class="button">{{classe.grade}}-->
                                        <!--                                </el-button>-->
                                        <el-tag>总 分：{{classe.sum}}</el-tag>
                                        <br/>
                                        <el-tag type="success">得 分：{{classe.grade}}</el-tag>
                                        <br/>
                                        <el-tag type="success" v-if="classe.grade>=80">合格</el-tag>
                                        <el-tag type="success" v-else>不合格</el-tag>
                                        <br/>
                                    </div>
                                </div>
                            </el-card>

                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="不合格">
                    <el-row :gutter="10">
                        <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                                v-for="classe in this.$store.state.MyClass.myTests" :key="classe.id">
                            <el-card class="data-card" :body-style="{ padding: '0px' }" v-if="classe.grade<80">
                                <el-image
                                        style="width: 100%; height: 150px"
                                        :src="classe.cover"
                                        fit="cover"></el-image>
                                <div style="padding: 14px;">
                                    <span>{{classe.title}}({{classe.tiid}})</span>
                                    <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                        <el-tag class="time">完成时间：{{classe.updatetime.split(' ')[0]}}</el-tag>
                                        <br/>
                                        <!--                                <el-button icon="el-icon-circle-check" type="success"-->
                                        <!--                                           class="button">{{classe.grade}}-->
                                        <!--                                </el-button>-->
                                        <el-tag>总 分：{{classe.sum}}</el-tag>
                                        <br/>
                                        <el-tag type="success">得 分：{{classe.grade}}</el-tag>
                                        <br/>
                                        <el-tag type="success" v-if="classe.grade>=80">合格</el-tag>
                                        <el-tag type="success" v-else>不合格</el-tag>
                                        <br/>
                                    </div>
                                </div>
                            </el-card>

                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="合格">
                    <el-row :gutter="10">
                        <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4" :offset="1"
                                v-for="classe in this.$store.state.MyClass.myTests" :key="classe.id">
                            <el-card class="data-card" :body-style="{ padding: '0px' }" v-if="classe.grade>=80">
                                <el-image
                                        style="width: 100%; height: 150px"
                                        :src="classe.cover"
                                        fit="cover"></el-image>
                                <div style="padding: 14px;">
                                    <span>{{classe.title}}({{classe.tiid}})</span>
                                    <!--                                <el-progress :text-inside="true" :stroke-width="10" :percentage="classe['duration']*100/classe['doc_duration']"></el-progress>-->
                                    <div class="bottom clearfix">
                                        <el-tag class="time">完成时间：{{classe.updatetime.split(' ')[0]}}</el-tag>
                                        <br/>
                                        <!--                                <el-button icon="el-icon-circle-check" type="success"-->
                                        <!--                                           class="button">{{classe.grade}}-->
                                        <!--                                </el-button>-->
                                        <el-tag>总 分：{{classe.sum}}</el-tag>
                                        <br/>
                                        <el-tag type="success">得 分：{{classe.grade}}</el-tag>
                                        <br/>
                                        <el-tag type="success" v-if="classe.grade>=80">合格</el-tag>
                                        <el-tag type="success" v-else>不合格</el-tag>
                                        <br/>
                                    </div>
                                </div>
                            </el-card>

                        </el-col>
                    </el-row>
                </el-tab-pane>
            </el-tabs>

        </el-tab-pane>

        <el-dialog v-loading="loading"
                   style="margin-top: -130px;"
                   :title="studyWindow.title"
                   :visible.sync="studyWindowEnable"
                   width="90%"
                   @open="$store.commit('AppIndex/setBottomEnable',true)"
                   @close="$store.commit('AppIndex/setBottomEnable',false)"
                   center>
            <component v-bind:is="this.studyWindow.instance"></component>
            <span slot="footer" class="dialog-footer">

            <el-button type="primary" v-on:click="studyWindowEnable=false">关 闭</el-button>
                    </span>
            <el-dialog
                    width="30%"
                    title="提示"
                    :visible.sync="studySubWinEnable"
                    append-to-body>
                <span>课程已完成</span>
                <span slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="closeAll()">确 定</el-button>
                </span>
            </el-dialog>
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
                },
                studySubWinEnable: false
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
                            if (this.studyWindow.instance === studyDoc) {
                                this.studyWindow.timer = setInterval(() => {
                                    console.log("doc exec timer")
                                    this.$store.dispatch("MyClass/updateDuration", this.studyWindow.currentClass.dlid).then(
                                        // eslint-disable-next-line no-unused-vars
                                        (resolve) => {
                                            console.log("update", resolve)
                                            this.$store.commit("AppIndex/setBottomPercent", resolve.percent)
                                            this.studyWindow.currentClass.duration += 10;
                                            if (this.$store.state.AppIndex.bottom.percent === 100) {
                                                clearInterval(this.studyWindow.timer)
                                                this.studySubWinEnable = true
                                            }
                                        },
                                        // eslint-disable-next-line no-unused-vars
                                        (reject) => {
                                            console.log("update", reject)

                                        }
                                    )

                                }, 10500)
                            } else if (this.studyWindow.instance === studyVidoe) {
                                this.studyWindow.timer = setInterval(() => {
                                    console.log("exec timer")
                                    this.$store.dispatch("MyClass/updateVideoDuration", this.studyWindow.currentClass.vlid).then(
                                        // eslint-disable-next-line no-unused-vars
                                        (resolve) => {
                                            console.log(resolve)
                                            this.$store.commit("AppIndex/setBottomPercent", resolve.percent)
                                            this.studyWindow.currentClass.duration += 10;
                                            console.log('cur time',this.studyWindow.currentClass.duration)
                                            if (this.$store.state.AppIndex.bottom.percent === 100) {
                                                clearInterval(this.studyWindow.timer)
                                                this.studySubWinEnable = true
                                            }
                                        },
                                        // eslint-disable-next-line no-unused-vars
                                        (reject) => {

                                        }
                                    )

                                }, 10500)
                            }

                        }
                        console.log(newVal)
                    }
                    if (newVal === false) {
                        console.log("clear timer")
                        clearInterval(this.studyWindow.timer)
                        this.studyWindow.instance = null;
                    }
                },
                deep: true,
            }
        },
        methods: {
            closeAll() {
                this.studySubWinEnable = false;
                this.studyWindowEnable = false;
                this.studyWindow.currentClass = null
            },
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
                this.studyWindow.currentClass = classe
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
        async created() {
            var count = 0;
            this.$store.commit('AppIndex/changeLoading', true);
            await this.$store.dispatch('MyClass/getMyDocs').then(
                // eslint-disable-next-line no-unused-vars
                (resolve) => {
                    count += 1
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
            await this.$store.dispatch('MyClass/getMyVideos')
                .then(
                    // eslint-disable-next-line no-unused-vars
                    (value) => {
                        count++

                    },
                    (err) => {
                        this.$notify({
                            message: err,
                            type: "error",
                            position: constant.NOTIFY_POS,
                        });
                    }
                )
            await this.$store.dispatch('MyClass/getMyTests')
                .then(
                    // eslint-disable-next-line no-unused-vars
                    (value) => {
                        count++
                    },
                    (err) => {
                        this.$notify({
                            message: err,
                            type: "error",
                            position: constant.NOTIFY_POS,
                        });
                    }
                )
            this.$store.commit('AppIndex/changeLoading', false);
            console.log(count)
            if (count === 3) {
                this.$notify({
                    message: "获取所有学习记录成功",
                    type: "success",
                    position: constant.NOTIFY_POS,
                });
            } else if (count > 0) {
                this.$notify({
                    message: "获取学习记录成功,但有些记录可能丢失",
                    type: "success",
                    position: constant.NOTIFY_POS,
                });
            } else if (count === 0) {
                this.$notify({
                    message: "获取学习记录失败",
                    type: "error",
                    position: constant.NOTIFY_POS,
                });
            }
        }
    }
</script>

<style scoped>
    .el-tabs {
        background: transparent;
    }

    .time {
        font-size: 13px;
        color: #999;
        /*text-align: left;*/
    }

    .clearfix {
        text-align: center;
        margin-bottom: 10px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .bottom {
        margin-top: 13px;
        line-height: 12px;
    }

    .el-card {
        box-shadow: 0 0 5px #cac6c6;
        min-height: 300px;
        /*background: azure;*/
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

    .el-tab-pane {
        min-height: 520px;
    }

</style>
