<template>
    <div>
        <el-form v-model="testData">
            <span>共 {{this.$store.state.doTest.testData.sum}} 分，共 {{this.$store.state.doTest.testData.num}} 题</span>
            <el-form-item v-for="(item,index) in testData" :key="index">
                <span>{{index+1}}. {{item.question}}({{item.score}}分)</span><br/>
                <el-radio-group v-if="item.type===0" v-model="item.answer">
                    <el-radio label="1">A.{{item.opa}}</el-radio>
                    <br/>
                    <el-radio label="2">B.{{item.opb}}</el-radio>
                    <br/>
                    <el-radio label="3">C.{{item.opc}}</el-radio>
                    <br/>
                    <el-radio label="4">D.{{item.opd}}</el-radio>
                    <br/>
                </el-radio-group>
                <el-checkbox-group v-else-if="item.type===1" v-model="item.answer">
                    <el-checkbox label="1">A.{{item.opa}}</el-checkbox>
                    <br/>
                    <el-checkbox label="2">B.{{item.opb}}</el-checkbox>
                    <br/>
                    <el-checkbox label="3">C.{{item.opc}}</el-checkbox>
                    <br/>
                    <el-checkbox label="4">D.{{item.opd}}</el-checkbox>
                    <br/>
                </el-checkbox-group>
                <el-radio-group v-else-if="item.type===2" v-model="item.answer">
                    <el-radio label="1">对</el-radio>
                    <br/>
                    <el-radio label="2">错</el-radio>
                    <br/>
                </el-radio-group>
                <br/>
            </el-form-item>

        </el-form>
    </div>
</template>

<script>
    export default {
        name: "doTest",
        data() {
            return {}
        },
        computed: {
            testData: {
                get() {
                    console.log('获取信息')
                    return this.$store.state.doTest.testData.topics;
                },
                set(value) {
                    console.log('答案修改')
                    this.$store.commit('doTest/changeAnswer', value)
                }
            }
        },
        watch: {
            // testData: {
            //     handler(newVal, oldVal) {
            //         console.log(newVal, oldVal)
            //         if (newVal.answer !== oldVal.answer) {
            //             this.$store.commit('doTest/changeAnswer', newVal.answer)
            //         }
            //     },
            //     deep: true
            // }
        },
        methods: {},
        created() {
        }
    }
</script>

<style scoped>

</style>
