<template>
    <div>
        <div style="z-index:3;float: right;position: relative">
            <el-input v-model="state" placeholder="请输入用户名或手机号码" suffix-icon="el-icon-search"></el-input>
        </div>
        <el-tabs type="border-card" :stretch="false">
            <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i>普通用户</span>
                <el-table stripe :data="this.tableData.filter((value)=>{if (value.admin===false) return true})">
                    <el-table-column prop="uiid" label="用户ID"></el-table-column>
                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="phonenum" label="手机号码"></el-table-column>
                    <el-table-column prop="updatetime" label="修改时间"></el-table-column>
                    <el-table-column prop="createtime" label="创建时间"></el-table-column>
                    <el-table-column
                            fixed="right"
                            label="操作"
                    >
                        <template slot-scope="scope">
                            <!--                            <el-button @click="resetPd(scope)" type="text" size="small">重置密码</el-button>-->
                            <el-popconfirm
                                    style="margin-left: 5px;"
                                    @onConfirm="resetPd(scope)"
                                    title="确定重置密码吗？"
                            >
                                <el-button slot="reference" type="text" size="small"> 重置密码</el-button>
                            </el-popconfirm>
                            <el-popconfirm
                                    style="margin-left: 5px;"
                                    @onConfirm="deleteUser(scope)"
                                    title="确定删除用户吗？"
                            >
                                <el-button slot="reference" type="text" size="small"> 删除</el-button>
                            </el-popconfirm>
                        </template>
                    </el-table-column>
                </el-table>
            </el-tab-pane>
                <el-tab-pane>
                    <span slot="label"><i class="el-icon-date"></i>管理员</span>
                    <el-table :data="this.tableData.filter((value)=>{if (value.admin===true) return true})">
                        <el-table-column prop="uiid" label="用户ID"></el-table-column>
                        <el-table-column prop="username" label="用户名"></el-table-column>
                        <el-table-column prop="phonenum" label="手机号码"></el-table-column>
                        <el-table-column prop="updatetime" label="修改时间"></el-table-column>
                        <el-table-column prop="createtime" label="创建时间"></el-table-column>
                        <el-table-column
                                fixed="right"
                                label="操作"
                        >
                            <template slot-scope="scope">
                                <!--                            <el-button @click="resetPd(scope)" type="text" size="small">重置密码</el-button>-->
                                <el-popconfirm
                                        style="margin-left: 5px;"
                                        @onConfirm="resetPd(scope)"
                                        title="确定重置密码吗？"
                                >
                                    <el-button slot="reference" type="text" size="small"> 重置密码</el-button>
                                </el-popconfirm>
                                <el-popconfirm
                                        style="margin-left: 5px;"
                                        @onConfirm="deleteUser(scope)"
                                        title="确定删除管理员吗？"
                                >
                                    <el-button slot="reference" type="text" size="small"> 删除</el-button>
                                </el-popconfirm>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>
    </div>
</template>

<script>
    import constant from "../../constant";

    export default {
        name: "adminUser",
        data() {
            return {
                filterArry: [],
                filterValue: [],
                restaurants: [],
                state: '',
                timeout: null
            }
        },
        computed: {
            tableData() {
                if (this.state) {
                    return this.$store.state.adminUser.userList.filter(
                        value => {
                            return Object.keys(value).some(key => {
                                    // indexOf() 返回某个指定的字符在某个字符串中首次出现的位置，如果没有找到就返回-1；
                                    // 该方法对大小写敏感！所以之前需要toLowerCase()方法将所有查询到内容变为小写。
                                    return String(value[key]).toLowerCase().indexOf(this.state) > -1
                                }
                            )
                        }
                    )
                }
                return this.$store.state.adminUser.userList;

            }
        },
        methods: {
            resetPd(data) {
                this.$store.dispatch('adminUser/resetPd', data.row).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
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
            },
            filterHandler(value, row, column) {
                const property = column['property'];
                return row[property] === value;
            },
            getUserList() {
                this.$store.dispatch('adminUser/getUserList').then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
                            position: constant.NOTIFY_POS,
                        })
                        this.$store.commit('adminUser/saveUserList', resolve.data)
                        this.restaurants = resolve.data;
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
            querySearchAsync(queryString, cb) {
                console.log(queryString, cb)
                var restaurants = this.restaurants;
                // eslint-disable-next-line no-unused-vars
                var results = restaurants.filter(item => true).map(
                    item => {
                        if (item.username.toLowerCase().indexOf(queryString.toLowerCase()) === 0) {
                            return {value: item.username}
                        }
                    }
                )

                clearTimeout(this.timeout);
                this.timeout = setTimeout(() => {
                    cb(results);
                }, 3000 * Math.random());
            },
            handSelect(item) {
                console.log(item, "item")
                console.log(this.state)
            },
            deleteUser(data){
                this.$store.dispatch('adminUser/deleteUser',data.row).then(
                    (resolve) => {
                        this.$notify({
                            type: "success",
                            message: resolve.msg,
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
        },
        created() {
            this.getUserList()
            // this.restaurants =  this.$store.state.adminUser.userList;
        }
    }
</script>

<style scoped>

    .el-tabs{
        background: transparent;
    }
    .el-table{
        background: transparent;
    }
</style>
