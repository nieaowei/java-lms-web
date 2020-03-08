import axios from "axios";
import constant from "../../constant";

const module_MyVideo = {
    namespaced: true,
    state: {
        myVideos: [],
    },
    getters: {},
    mutations: {
        saveMyVideos(state, data) {
            state.myVideos = data
        }
    },
    actions: {
        getMyDocs({commit}) {
            return new Promise((resolve, reject) => {
                axios.get(
                    'api/user/video/record',
                    {
                        timeout: 3000
                    }
                ).then(
                    (success) => {
                        if (success.data.status === 200) {
                            commit('saveMyVideos', success.data.data);
                            resolve(success.data.msg)
                        }
                        reject(success.data.msg)
                    }
                ).catch(
                    (fail) => {
                        if (fail.response.status === 302) {
                            reject(constant.REDIRECT_LOGIN)
                        }
                        reject(constant.SERVER_FAIL)
                    }
                );
            });
        },
        // eslint-disable-next-line no-unused-vars
        updateDuration({commit},_dlid){
            return new Promise((resolve, reject) => {
                axios.post(
                    "api/user/video/update",
                    {
                        dlid: _dlid
                    }
                ).then(
                    (success) => {
                        if (success.data.status === 200) {
                            resolve(success.data.data)
                        }
                        reject(success.data.msg)
                    }
                ).catch(
                    (fail) => {
                        if (fail.response.status === 302) {
                            reject(constant.REDIRECT_LOGIN)
                        }
                        reject(constant.SERVER_FAIL)
                    }
                );
            })
        }
    },
}
export default module_MyVideo;