<template>
    <el-card>
        <el-page-header @back="goBack" content="详情页面">
        </el-page-header>
        <pdf src="/123.pdf">dsada</pdf>

    </el-card>

</template>

<script>
    import pdf from 'vue-pdf'
    export default {
        name:'studyDoc',
        components:{pdf}
    }
</script>

<style scoped>

</style>