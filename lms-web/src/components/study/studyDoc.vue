<template>
    <div class="pdf1">
<!--        <div class="pdf-tab">-->
<!--            <div :class="['btn-def',{'btn-active':activeIndex==index}]"-->
<!--                    v-for="(item,index) in pdfList" :key="item.id"-->
<!--                    @click.stop="pdfClick(item.pdfUrl,index)">-->
<!--                {{item.title}}-->
<!--            </div>-->
<!--        </div>-->

        <el-carousel arrow="always" style="width: 100%" :loop="false" :initial-index="0" height="900px" type="card" :autoplay="false">
            <el-carousel-item style="width: 50%;border: black solid 1px;" v-for="i in this.$store.state.studyDoc.PdfPageNum" :key="i">
                <pdf
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
        // mounted: function() {
        //     this.pdfTask(this.pdfList[0].pdfUrl)
        // },
        methods: {
            // pdfTask(pdfUrl){
            //     var self = this
            //     var loadingTask = pdf.createLoadingTask(pdfUrl)
            //     loadingTask.then(pdf => {
            //         self.pdfUrl = loadingTask
            //         self.numPages = pdf.numPages
            //         // eslint-disable-next-line no-unused-vars
            //     }).catch((err) => {
            //         console.error('pdf加载失败')
            //     })
            // },
            // pdfClick(pdfUrl,index){
            //     if(index === this.activeIndex)return
            //     this.activeIndex = index
            //     this.pdfUrl = null
            //     this.pdfTask(pdfUrl)
            // },
        }
    }
</script>

<style scoped>

    .pdf{

    }


</style>