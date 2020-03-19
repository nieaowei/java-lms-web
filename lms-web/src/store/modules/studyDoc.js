import pdf from 'vue-pdf'
import axios from 'axios'
import constant from "../../constant";

const module_studyDoc = {
    namespaced: true,
    state: {
        PdfData: '',
        PdfPageNum: 0,
    },
    mutations: {
        savePdfData(state, data) {
            state.PdfData = data;
        },
        savePdfPageNum(state, data) {
            state.PdfPageNum = data;
        }

    },
    actions: {
        loadPdfData({commit}, dlid) {
            return new Promise(
                (resolve1, reject1) => {
                    // var flag =false;
                    axios.get(
                        "api/doc/getpath?dlid=" + dlid,
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                var task = pdf.createLoadingTask(success.data.data)
                                task.then(
                                    (pdf1) => {
                                        commit('savePdfData', task);
                                        commit('savePdfPageNum', pdf1.numPages)
                                        resolve1("文档获取成功")
                                    }
                                ).catch(
                                    (fail) => {
                                        console.log(fail)
                                        commit('savePdfData', '');
                                        commit('savePdfPageNum', 0)
                                        reject1("文档获取失败")

                                    }
                                )
                            }else{
                                commit('savePdfData', '');
                                commit('savePdfPageNum', 0)
                                reject1("文档获取失败")
                            }
                        }
                    ).catch(
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject1(constant.REDIRECT_LOGIN)
                            }
                            reject1(constant.SERVER_FAIL)
                        }
                    )
                }
            )

        }
    }
}

export default module_studyDoc;