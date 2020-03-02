import pdf from 'vue-pdf'

const module_studyDoc={
    state:{
        PdfData: null,
        PdfPageNum:0,
    },
    mutations:{
        savePdfData(state,data){
            state.PdfData = data;
        },
        savePdfPageNum(state,data){
            state.PdfPageNum=data;
        }

    },
    actions:{
        loadPdfData({commit},path){
            return new Promise(
                (resolve,reject)=>{
                    var task = pdf.createLoadingTask(path)
                    task.then(
                        (pdf)=>{
                            commit('savePdfData',task);
                            commit('savePdfPageNum',pdf.numPages)
                            resolve('文档获取成功')
                        }
                    ).catch(
                        (fail)=>{
                            console.log(fail)
                            reject('文档获取失败')
                        }
                    )
                }
            )

        }
    }
}

export default module_studyDoc;