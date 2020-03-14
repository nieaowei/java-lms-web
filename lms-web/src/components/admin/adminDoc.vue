<template>
    <div>
        <!--        <div>-->
        <div style="z-index:3;float: right;position: relative">
            <el-button style="margin-left: 5px" @click="addNewTab">新增文档</el-button>
            <el-input style="margin-left:5px;margin-bottom: 1px;width: 300px" placeholder="请输入内容"
                      v-model="searchContext" class="input-with-select">
                <!--                <el-select v-model="select" slot="prepend" placeholder="请选择" >-->
                <!--                    <el-option label="餐厅名" value="1"></el-option>-->
                <!--                    <el-option label="订单号" value="2"></el-option>-->
                <!--                    <el-option label="用户电话" value="3"></el-option>-->
                <!--                </el-select>-->
                <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
        </div>
        <!--        </div>-->
        <el-tabs v-model="editableTabsValue" type="card" @tab-remove="removeTab" @tab-click="clickTab">
            <el-tab-pane label="文档查询">
                <el-table
                        :data="this.$store.state.adminDoc.docList"
                        height="520px"
                        style="width: 100%">
                    <el-table-column
                            fixed
                            prop="dlid"
                            label="课程号"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="name"
                            label="课程名"
                    >
                    </el-table-column>
                    <el-table-column
                            prop="duration"
                            label="课程时长"
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
                            <el-button type="text" size="small" @click="addEdit(scope)">编辑</el-button>
                            <!--                            <el-button type="text" size="small">删除</el-button>-->
                            <el-popconfirm
                                    @onConfirm="deleteDoc(scope)"
                                    title="确定删除该课程吗？"
                            >
                                <el-button slot="reference" type="text" size="small"> 删除</el-button>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
                <!--                <el-pagination-->
                <!--                        @size-change="handleSizeChange"-->
                <!--                        @current-change="handleCurrentChange"-->
                <!--                        :current-page.sync="currentPage3"-->
                <!--                        :page-size="100"-->
                <!--                        layout="prev, pager, next, jumper"-->
                <!--                        :total="1000">-->
                <!--                </el-pagination>-->
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
                    <el-form label-position="left" label-width="100px" v-model="item.form">
                        <el-form-item label="封面图片：">
                            <el-input v-model="item.form.cover"></el-input>
                        </el-form-item>
                        <el-form-item label="PDF文件：">
                            <el-input style="" v-model="item.form.path"></el-input>
                            <el-upload
                                    action="https://jsonplaceholder.typicode.com/posts/"
                                    :show-file-list="false">
                                <el-button size="small" type="primary">点击上传</el-button>
                            </el-upload>
                        </el-form-item>
                        <el-form-item label="课程名：">
                            <el-input v-model="item.form.name"></el-input>
                        </el-form-item>
                        <el-form-item label="学习时长：">
                            <el-input v-model="item.form.duration"></el-input>
                        </el-form-item>
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
                                    <span>{{item.data.cover}}</span>

                                </el-form-item>
                                <el-form-item label="文件地址：">
                                    <span>{{item.data.path}}</span>
                                </el-form-item>
                                <el-form-item label="课程号：">
                                    <span>{{item.data.dlid}}</span>
                                </el-form-item>
                                <el-form-item label="课程名：">
                                    <span>{{item.data.name}}</span>
                                </el-form-item>
                                <el-form-item label="学习时长：">
                                    <span>{{item.data.duration}}</span>
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
                        <span>已添加学习用户：</span>
                        <el-table :data="item.data.users">
                            <el-table-column
                                    prop="uiid"
                                    label="用户ID"
                            ></el-table-column>
                            <el-table-column
                                    prop="username"
                                    label="用户名"
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
        name: "adminDoc",
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
        methods: {
            addEdit(data){
                let newTabName = ++this.tabIndex + '';
                this.editableTabs.push({
                    title: '编辑文档',
                    name: newTabName,
                    type: 'newDoc',
                    action:'edit',
                    form: {
                        dlid: data.row.dlid,
                        name: data.row.name,
                        cover: data.row.cover,
                        path: data.row.path,
                        duration: data.row.duration,
                    }
                });
                // this.curTab++;
                this.curTab = this.editableTabs.length;
                this.editableTabsValue = newTabName;
            },

            updateDoc(item) {
                console.log(item)
                this.$store.dispatch('adminDoc/updateDoc', item.form).then(
                    (resolve) => {
                        console.log('yes')
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        //@todo 增加到本地文档列表
                        // this.$store.commit('DocClass')
                        this.removeTab(item.name)
                        this.$store.commit('adminDoc/changeDoc', {dlid: item.form.dlid, item: resolve.data})
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
                this.$store.dispatch('adminDoc/deleteDoc', data.row).then(
                    (resolve) => {
                        console.log('yes')
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('adminDoc/removeDoc', data.$index)
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
                this.$store.dispatch("adminDoc/addDoc", item.form).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('adminDoc/addDoc', resolve.data)
                        //@todo 增加到本地文档列表
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
                    title: '新建文档',
                    name: newTabName,
                    type: 'newDoc',
                    action:'new',
                    form: {
                        name: '',
                        cover: '',
                        path: '',
                        duration: 0,
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
                this.$store.dispatch('adminDoc/getDetail', data.row).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
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
                    title: '文档详情',
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
</style>