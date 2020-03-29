<template>
    <div>
        <!--        <div>-->
        <div style="z-index:3;float: right;position: relative">
            <el-button style="margin-left: 5px" @click="addNewTab">新增试卷</el-button>
            <el-input style="margin-left:5px;margin-bottom: 1px;width: 300px" placeholder="请输入内容"
                      v-model="searchContext" class="input-with-select">
                <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
        </div>
        <!--        </div>-->
        <el-tabs v-model="editableTabsValue" type="card" @tab-remove="removeTab" @tab-click="clickTab">
            <el-tab-pane label="试卷查询">
                <el-table
                        :data="tableData"
                        height="520px"
                        style="width: 100%">
                    <el-table-column
                            fixed
                            prop="tiid"
                            label="试卷号"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="title"
                            label="试卷名"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="num"
                            label="题数"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="sum"
                            label="总分"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="username"
                            label="上传人"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="updatetime"
                            label="修改日期"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="createtime"
                            label="创建日期"
                    >
                    </el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                    >
                        <template slot-scope="scope">
                            <el-button @click="getDetail(scope)" type="text" size="small">详情</el-button>
                            <el-button type="text" size="small" @click="addEdit(scope)">编辑</el-button><br/>
                            <!--                            <el-button type="text" size="small">删除</el-button>-->
                            <el-popconfirm
                                    @onConfirm="deleteDoc(scope)"
                                    title="确定删除该试卷吗？"
                            >
                                <el-button slot="reference" type="text" size="small"> 删除</el-button>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
            <el-tab-pane
                    :key="item.name"
                    v-for="(item) in editableTabs"
                    :label="item.title"
                    :name="item.name"
                    closable

            >
                <div v-if="item.type==='newDoc'" style="margin-left: 30%;width: 400px;text-align: center">
                    <el-upload
                            class="upload-demo"
                            drag
                            action="api/admin/uploadimage/"
                            :on-success="handleAvatarSuccess"
                            :show-file-list="false"
                    >
                        <el-image
                                v-if="item.form.cover" :src="item.form.cover"
                                style="width: 100%; height: 100%"
                                fit="fit">
                        </el-image>
                        <div v-else>
                            <span>封面图片</span><br/>
                            <i class="el-icon-upload"></i>
                            <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                        </div>
                    </el-upload>
                    <el-form label-position="left" v-model="item.form">
                        <el-form-item label="封面图片：">
                            <el-input v-model="item.form.cover"></el-input>
                        </el-form-item>
                        <el-form-item label="试卷名：">
                            <el-input v-model="item.form.title"></el-input>
                        </el-form-item>
                        <el-card v-for="(topic, index) in item.form.topics" :key="index"
                                 style="margin-top: 5px;"
                        >
                            <!--                            style="border: #409EFF dashed 1px;padding: 5px"-->
                            <el-form-item>


                                <div v-if="topic.type===0">
                                    <span>选择题 {{index+1}}</span><br/>
                                    <el-input placeholder="题目" v-model="topic.question">问题</el-input>
                                    <br/>
                                    <el-radio-group v-model="topic.answer">
                                        <el-radio label="1">
                                            A.
                                            <el-input v-model="topic.opa"></el-input>
                                            <br/>
                                        </el-radio>
                                        <br/>
                                        <el-radio label="2">
                                            B.
                                            <el-input v-model="topic.opb"></el-input>
                                            <br/>
                                        </el-radio>
                                        <br/>
                                        <el-radio label="3">
                                            C.
                                            <el-input v-model="topic.opc"></el-input>
                                            <br/>
                                        </el-radio>
                                        <br/>
                                        <el-radio label="4">
                                            D.
                                            <el-input v-model="topic.opd"></el-input>
                                            <br/>
                                        </el-radio>
                                        <br/>
                                    </el-radio-group>
                                    <!--                                <el-input placeholder="选项B" v-model="topic.opb"></el-input>-->
                                    <!--                                <el-input placeholder="选项C" v-model="topic.opc"></el-input>-->
                                    <!--                                <el-input placeholder="选项D" v-model="topic.opd"></el-input>-->
                                    <!--                                <el-input placeholder="答案" v-model="topic.answer"></el-input>-->
                                </div>
                                <div v-else-if="topic.type===1">
                                    <span>多选题 {{index+1}}</span><br/>
                                    <el-input placeholder="题目" v-model="topic.question">问题</el-input>
                                    <br/>
                                    <el-checkbox-group v-model="topic.answer">
                                        <el-checkbox label="1">
                                            A.
                                            <el-input v-model="topic.opa"></el-input>
                                            <br/>
                                        </el-checkbox>
                                        <br/>
                                        <el-checkbox label="2">
                                            B.
                                            <el-input v-model="topic.opb"></el-input>
                                            <br/>
                                        </el-checkbox>
                                        <br/>
                                        <el-checkbox label="3">
                                            C.
                                            <el-input v-model="topic.opc"></el-input>
                                            <br/>
                                        </el-checkbox>
                                        <br/>
                                        <el-checkbox label="4">
                                            D.
                                            <el-input v-model="topic.opd"></el-input>
                                            <br/>
                                        </el-checkbox>
                                        <br/>
                                    </el-checkbox-group>
                                    <!--                                <el-input placeholder="选项A" v-model="topic.opa"></el-input>-->
                                    <!--                                <el-input placeholder="选项B" v-model="topic.opb"></el-input>-->
                                    <!--                                <el-input placeholder="选项C" v-model="topic.opc"></el-input>-->
                                    <!--                                <el-input placeholder="选项D" v-model="topic.opd"></el-input>-->
                                    <!--                                <el-input placeholder="答案" v-model="topic.answer"></el-input>-->
                                </div>
                                <div v-else-if="topic.type===2">
                                    <span>判断题 {{index+1}}</span>
                                    <el-input placeholder="题目" v-model="topic.question">问题</el-input>
                                    <el-radio-group v-model="topic.answer">
                                        <el-radio label="1">对</el-radio>
                                        <el-radio label="2">错</el-radio>

                                    </el-radio-group>
                                </div>

                            </el-form-item>
                        </el-card>

                        <el-button v-if="item.action==='new'" v-on:click="commit(item)">提交</el-button>
                        <el-button v-if="item.action==='edit'" v-on:click="updateDoc(item)">提交</el-button>
                    </el-form>
                </div>
                <div v-else-if="item.type==='newDetail'">
                    <div style="float: left;width: 50%;text-align: center">
                        <div style="width: 70%;margin-left: 10%">
                            <el-image
                                    v-if="item.data.cover" :src="item.data.cover"
                                    style="width: 100%; height: 200px;border: #409EFF dashed 1px"
                                    fit="fit">
                            </el-image>
                            <el-form label-width="100px" disabled>
                                <el-form-item label="封面地址：">
                                    <!--                                    <span >{{item.data.cover}}</span>-->
                                    <el-input
                                            style=""
                                            type="textarea"
                                            autosize
                                            v-model="item.data.cover">
                                    </el-input>
                                </el-form-item>
                                <el-form-item label="试卷号：">
                                    <span>{{item.data.tiid}}</span>
                                </el-form-item>
                                <el-form-item label="试卷名：">
                                    <span>{{item.data.title}}</span>
                                </el-form-item>
                                <el-form-item label="试卷总分：">
                                    <span>{{item.data.sum}}</span>
                                </el-form-item>
                                <el-form-item label="试题总数：">
                                    <span>{{item.data.num}}</span>
                                </el-form-item>
                                <el-form-item label="上传人：">
                                    <span>{{item.data.username}}</span>
                                </el-form-item>
                                <el-form-item label="修改时间：">
                                    <span>{{item.data.updatetime}}</span>
                                </el-form-item>
                                <el-form-item label="创建时间：">
                                    <span>{{item.data.createtime}}</span>
                                </el-form-item>
                            </el-form>
                        </div>

                    </div>
                    <div style="float:right;width: 50%;text-align: left">
                        <span>已完成试卷用户：</span>
                        <el-table :data="item.data.users" height="750px">
                            <el-table-column
                                    prop="uiid"
                                    label="用户ID"
                            ></el-table-column>
                            <el-table-column
                                    prop="username"
                                    label="用户名"
                            ></el-table-column>
                            <!--                            <el-table-column-->
                            <!--                                    prop="grade"-->
                            <!--                                    label="成绩"-->
                            <!--                            ></el-table-column>-->
                        </el-table>
                    </div>
                    <div style="width: 100%;float: left">
                        <el-divider style="width: 100%;position: relative">题目列表</el-divider>

                        <el-table :data="item.data.topics">
                            <el-table-column
                                    prop="tcid"
                                    label="题目号"
                            ></el-table-column>
                            <el-table-column
                                    prop="question"
                                    label="问题"
                            ></el-table-column>
                            <el-table-column
                                    prop="type"
                                    label="题型"
                            ></el-table-column>
                            <el-table-column
                                    prop="opa"
                                    label="选项A"
                            ></el-table-column>
                            <el-table-column
                                    prop="opb"
                                    label="选项B"
                            ></el-table-column>
                            <el-table-column
                                    prop="opc"
                                    label="选项C"
                            ></el-table-column>
                            <el-table-column
                                    prop="opd"
                                    label="选项D"
                            ></el-table-column>
                            <el-table-column
                                    prop="answer"
                                    label="答案"
                            ></el-table-column>
                            <el-table-column
                                    prop="score"
                                    label="分数"
                            ></el-table-column>
                        </el-table>
                    </div>
                </div>
            </el-tab-pane>
        </el-tabs>

    </div>

</template>

<script>
    import constant from "../../constant";

    export default {
        name: "adminTest",
        data() {
            return {
                searchContext: '',
                editableTabsValue: '0',
                editableTabs: [],
                tabIndex: 0,
                curTab: 0,
                imageUrl: ''
            }
        },
        computed:{
            tableData(){
                if (this.searchContext) {
                    return this.$store.state.adminTest.testList.filter(
                        value => {
                            return Object.keys(value).some(key => {
                                    // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
                                    // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
                                    return String(value[key]).toLowerCase().indexOf(this.searchContext) > -1
                                }
                            )
                        }
                    )
                }
                return this.$store.state.adminTest.testList;
            }
        },
        methods: {
            removeTopic(topics, topic) {
                var index = topics.indexOf(topic)
                if (index !== -1) {
                    topics.splice(index, 1)
                }
            },
            addTopic(topics) {
                topics.push({
                    question: '',
                    opa: '',
                    opb: '',
                    opc: '',
                    opd: '',
                    score: '',
                    answer: '',
                })
            },
            addEdit(data) {
                this.$store.dispatch('adminTest/getDetail', data.row).then(
                    (success) => {
                        success.data.topics.forEach(
                            value => {
                                if (value.type === 1) {
                                    value.answer = value.answer.split(',')
                                }
                            }
                        )
                        let newTabName = ++this.tabIndex + '';
                        this.editableTabs.push({
                            title: '编辑试卷',
                            name: newTabName,
                            type: 'newDoc',
                            action: 'edit',
                            form: {
                                tiid: data.row.tiid,
                                title: data.row.title,
                                cover: data.row.cover,
                                topics: success.data.topics,
                                createtime: data.row.createtime,
                                updatetime: data.row.updatetime,
                            }
                        });
                        // this.curTab++;
                        this.curTab = this.editableTabs.length;
                        this.editableTabsValue = newTabName;
                    }
                )

            },

            updateDoc(item) {
                console.log(item)
                this.$store.dispatch('adminTest/updateTest', item.form).then(
                    (resolve) => {
                        console.log('yes')
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        //@todo 增加到本地试卷列表
                        // this.$store.commit('DocClass')
                        console.log('update result', resolve)
                        this.removeTab(item.name)
                        this.$store.commit('adminTest/changeTest', {tiid: resolve.data.tiid, newitem: resolve.data})
                    },
                    (reject) => {
                        console.log(reject)

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
            deleteDoc(data) {
                this.$store.dispatch('adminTest/deleteTest', data.row).then(
                    (resolve) => {
                        console.log('yes')
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('adminTest/removeTest', data.$index)
                    },
                    (reject) => {
                        console.log(reject)

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
            clickTab(tab) {
                console.log('click')
                console.log(tab.index)
                console.log(this.editableTabsValue)
                this.curTab = tab.index
            },
            // eslint-disable-next-line no-unused-vars
            commit(item) {
                this.$store.dispatch("adminTest/addTest", item.form).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('adminTest/addTest', resolve.data)
                        //@todo 增加到本地试卷列表
                        // this.$store.commit('DocClass')
                        this.removeTab(item.name)
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
            removeTab(name) {
                let tabs = this.editableTabs;
                let activeName = this.editableTabsValue;
                console.log('remove qian :', this.curTab)

                tabs.forEach((tab, index) => {
                    if (tab.name === name) {
                        if (activeName === name) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                                if (nextTab === tabs[index + 1]) {
                                    this.curTab = index + 1;
                                } else {
                                    this.curTab = index;
                                }
                            }
                        } else {
                            if (this.curTab > index + 1) {
                                this.curTab--
                            }
                        }
                    }

                });

                // if (activeName === name) {
                //     tabs.forEach((tab, index) => {
                //         if (tab.name === name) {
                //             let nextTab = tabs[index + 1] || tabs[index - 1];
                //             if (nextTab) {
                //                 activeName = nextTab.name;
                //                 if (nextTab === tabs[index+1]){
                //                     this.curTab = index + 1;
                //                 }else{
                //                     this.curTab = index;
                //                 }
                //             }
                //         }
                //     });
                // } else {
                //     this.curTab--;
                // }

                this.editableTabsValue = activeName;
                this.editableTabs = tabs.filter(tab => tab.name !== name);
                if (this.editableTabs.length === 0) {
                    this.editableTabsValue = '0'
                    this.curTab = 0;
                }
                console.log('remove hou :', this.curTab)
            },
            addNewTab() {
                let newTabName = ++this.tabIndex + '';
                this.editableTabs.push({
                    title: '新建试卷',
                    name: newTabName,
                    type: 'newDoc',
                    action: 'new',
                    form: {
                        title: '',
                        cover: '',
                        topics: [{
                            question: '',
                            type: 0,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: '',
                        }, {
                            question: '',
                            type: 0,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: '',
                        }, {
                            question: '',
                            type: 0,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: '',
                        }, {
                            question: '',
                            type: 0,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: '',
                        }, {
                            question: '',
                            type: 0,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: '',
                        }, {
                            question: '',
                            type: 1,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: [],
                        }, {
                            question: '',
                            type: 1,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: [],
                        }, {
                            question: '',
                            type: 1,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: [],
                        }, {
                            question: '',
                            type: 2,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: '',
                        }, {
                            question: '',
                            type: 2,
                            opa: '',
                            opb: '',
                            opc: '',
                            opd: '',
                            answer: '',
                        },]
                    }
                });
                // this.curTab++;
                this.curTab = this.editableTabs.length;
                this.editableTabsValue = newTabName;
                console.log("new tab :", this.curTab)
                console.log(this.tabIndex)
                console.log(this.editableTabsValue)
                console.log(this.editableTabs)
            },
            getDetail(data) {
                this.$store.dispatch('adminTest/getDetail', data.row).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        resolve.data.topics.forEach(
                            value => {
                                if (value.type === 0) {
                                    value.type = '选择题'
                                    if (value.answer === '1') {
                                        value.answer = 'A'
                                    } else if (value.answer === '2') {
                                        value.answer = 'B'
                                    } else if (value.answer === '3') {
                                        value.answer = 'C'
                                    } else if (value.answer === '4') {
                                        value.answer = 'D'
                                    }
                                } else if (value.type === 1) {
                                    value.type = '多选题'
                                    var temp = value.answer.split(',')
                                    console.log(temp)
                                    temp.forEach(
                                        // eslint-disable-next-line no-unused-vars
                                        (value1, index) => {
                                            console.log(value1)
                                            if (value1 === "1") {
                                                temp[index] = 'A'
                                            } else if (value1 === "2") {
                                                temp[index] = 'B'

                                            } else if (value1 === "3") {
                                                temp[index] = 'C'

                                            } else if (value1 === "4") {
                                                temp[index] = 'D'
                                            }
                                        }
                                    )
                                    console.log(temp.toString())
                                    value.answer = temp.toString()
                                } else if (value.type === 2) {
                                    value.type = '判断题'
                                    if (value.answer === '1') {
                                        value.answer = '对'
                                    } else if (value.answer === '2') {
                                        value.answer = '错'
                                    }
                                }

                            }
                        )
                        this.addNewDetail(resolve.data)
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
            addNewDetail(data) {
                let newTabName = ++this.tabIndex + '';
                this.editableTabs.push({
                    title: '试卷详情',
                    name: newTabName,
                    type: 'newDetail',
                    data: data,
                });
                this.curTab = this.editableTabs.length;
                this.editableTabsValue = newTabName;
                console.log("new detail :", this.curTab)
                console.log(this.tabIndex)
                console.log(this.editableTabsValue)
                console.log(this.editableTabs)
            },
            // eslint-disable-next-line no-unused-vars
            handleAvatarSuccess(res, file) {
                console.log(this.curTab)
                this.editableTabs[this.curTab - 1].form.cover = res.data;
            },
        },
        created() {
            this.$store.dispatch('adminTest/getTestList').then(
                // eslint-disable-next-line no-unused-vars
                (resolve) => {
                    // this.$notify({
                    //     type: "success",
                    //     message: resolve,
                    //     position: constant.NOTIFY_POS,
                    // })
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

<style scoped>
    .avatar-uploader .el-upload {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
    }

    .avatar-uploader .el-upload:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }

    .el-radio {
        margin-top: 3px;
    }

    .el-checkbox {
        margin-top: 3px;
    }

    /*.el-form-item div .el-checkbox-group .el-checkbox .el-input{*/
    /* width: 120%;*/
    /*}*/
</style>
