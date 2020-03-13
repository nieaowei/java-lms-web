<template>
    <div>
        <!--        <div>-->
        <div style="z-index:3;float: right;position: relative">
            <el-button style="margin-left: 5px" @click="addNewTab">新增文档</el-button>
            <el-input style="margin-left:5px;width: 200px">搜索</el-input>
            <el-button style="margin-left: 5px">搜索</el-button>
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
                            <el-button @click="handleClick(scope.row)" type="text" size="small">详情</el-button>
                            <el-button type="text" size="small">编辑</el-button>
                            <el-button type="text" size="small">删除</el-button>
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
                <div style="margin-left: 30%;width: 400px;text-align: center">
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
                        <el-button v-on:click="commit(item)">提交</el-button>
                    </el-form>
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
                editableTabsValue: '0',
                editableTabs: [],
                tabIndex: 0,
                curTab:0,
                imageUrl: ''
            }
        },
        methods: {
            clickTab(tab){
                console.log('click')
                console.log(tab.index)
                console.log(this.editableTabsValue)
                this.curTab = tab.index
            },
            // eslint-disable-next-line no-unused-vars
            commit(item){
                this.$store.dispatch("adminDoc/addDoc",item.form).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('adminDoc/addDoc',resolve.data)
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
            removeTab(name){
                let tabs = this.editableTabs;
                let activeName = this.editableTabsValue;
                console.log('remove qian :' ,this.editableTabsValue)

                if (activeName === name) {
                    tabs.forEach((tab, index) => {
                        if (tab.name === name) {
                            let nextTab = tabs[index + 1] || tabs[index - 1];
                            if (nextTab) {
                                activeName = nextTab.name;
                            }
                        }
                    });
                }

                this.editableTabsValue = activeName;
                this.editableTabs = tabs.filter(tab => tab.name !== name);
                if (this.editableTabs.length === 0){
                    this.editableTabsValue = '0'
                }
                console.log('remove hou :' ,this.editableTabsValue)
            },
            addNewTab() {
                let newTabName = ++this.tabIndex + '';
                this.editableTabs.push({
                    title: '新建文档',
                    name: newTabName,
                    form: {
                        name: '',
                        cover: '',
                        path: '',
                        duration:0,
                    }
                });
                this.curTab++;
                this.editableTabsValue = newTabName;
                console.log(this.curTab)
                console.log(this.tabIndex)
                console.log(this.editableTabsValue)
                console.log(this.editableTabs)
            },
            // eslint-disable-next-line no-unused-vars
            handleAvatarSuccess(res, file) {
                console.log(res)
                this.editableTabs[this.curTab-1].form.cover =res.data;
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