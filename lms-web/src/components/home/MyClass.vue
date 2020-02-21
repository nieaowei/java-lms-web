<template>
    <el-card body-style="padding:1px">
        <el-tabs type="card" stretch="true">
            <el-tab-pane>
                <span slot="label"><i class="el-icon-date"></i> 学习文档</span>
                <el-row :gutter="10" >
                    <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4"  :offset="1" v-for="fit in fits" :key="fit" >
                        <el-card :body-style="{ padding: '0px' }">
                            <el-image
                                    :src="url"
                                    :fit="fit"></el-image>
                            <div style="padding: 14px;">
                                <span>语文课</span>
                                <el-progress :percentage="50"></el-progress>
                                <div class="bottom clearfix">
                                    <time class="time">{{ currentDate }}</time>
                                    <el-button type="primary" class="button">开始学习</el-button>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-tab-pane>

            <el-tab-pane label="学习视频">
                <el-row :gutter="10" >
                    <el-col :xs="12" :sm="10" :md="8" :lg="6" :xl="4"  :offset="1" v-for="fit in fits" :key="fit" >
                        <el-card :body-style="{ padding: '0px' }">
                            <el-image
                                    :src="url"
                                    :fit="fit"></el-image>
                            <div style="padding: 14px;">
                                <span>属性课</span>
                                <el-progress :percentage="50"></el-progress>
                                <div class="bottom clearfix">
                                    <time class="time">{{ currentDate }}</time>
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
        </el-tabs>
    </el-card>

</template>

<script>
    export default {
        name: "MyClass",
        data() {
            return {
                coll_active: ['1','2','3'],
                fits: 'fill',
                url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg'
            }
        }
    }
</script>

<style scoped>
    .el-card{
        box-shadow: 0 0 5px #cac6c6;
    }
    .el-card:hover{
        box-shadow: 0 0 30px #cac6c6;
    }
    .el-card{
        margin-bottom: 20px;
    }
</style>