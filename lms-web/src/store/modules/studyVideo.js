import axios from 'axios'
import constant from "../../constant";

const module_studyVideo = {
    namespaced: true,
    state: {
        VideoData: '',
    },
    mutations: {
        saveVideoData(state, data) {
            state.VideoData = data;
        },
        savePdfPageNum(state, data) {
            state.PdfPageNum = data;
        }

    },
    actions: {
        loadVideoData({commit}, vlid) {
            return new Promise(
                (resolve1, reject1) => {
                    // var flag =false;
                    axios.get(
                        "api/video/getpath?vlid=" + vlid,
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                resolve1(success.data.msg)
                                commit('saveVideoData', success.data.data);
                            }else{
                                commit('saveVideoData', '');
                                // commit('savePdfPageNum', 0)
                                reject1(success.data.msg)
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

export default module_studyVideo;