<template>
    <iframe width="100%" height="900px" :src="pdfData"></iframe>
</template>
<script>
    import pdf from 'vue-pdf'
    export default {
        name: 'studyDoc',
        components:{
            // eslint-disable-next-line vue/no-unused-components
            pdf
        },
        data(){
            return {
                data1: this.$store.state.studyDoc.PdfData,
                scale:100,
            }
        },
        computed:{
          pdfData(){
              return this.$store.state.studyDoc.PdfSrc
          },
        },

        methods: {

        }
    }
</script>

<style scoped>

    .pdf{

    }


</style>
