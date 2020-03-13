<template>
    <div class="pdf1">
        <el-carousel arrow="always" style="width: 100%" :loop="false" :initial-index="0" height="900px" type="card" :autoplay="false">
            <el-carousel-item  v-for="i in this.$store.state.studyDoc.PdfPageNum" :key="i">
                <pdf style="width: 90%;border: black solid 1px;"
                        :src="$store.state.studyDoc.PdfData"
                        :page="i">
                </pdf>
            </el-carousel-item>
        </el-carousel>
    </div>
</template>
<script>
    import pdf from 'vue-pdf'
    export default {
        name: 'studyDoc',
        components:{
            pdf
        },
        data(){
            return {
                data1: this.$store.state.studyDoc.PdfData
            }
        },

        methods: {

        }
    }
</script>

<style scoped>

    .pdf{

    }


</style>