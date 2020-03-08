import axios from 'axios'
import constant from "../../constant";

const module_VideoClass = {
    namespaced: true,

    state: {
        videoList: [],
    },
    getters: {},
    mutations: {
        saveVideoList(state, data) {
            state.videoList = data;
        },

    },
    actions: {
        getVideoList({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/video/all/person",
                        {timeout: 3000}
                    ).then(
                        (success) => {
                            if (success.data['status'] === 200) {
                                commit('saveVideoList',success.data['data']);
                                resolve(success.data['msg']);
                            }
                            reject(success.data['msg']);
                        }
                    ).catch(
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject(constant.REDIRECT_LOGIN)
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    )
                }
            );
        },
        addVideoLearn({commit},vlid_){
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/user/video/addRecord",
                        {
                            vlid:vlid_
                        },
                        {
                            timeout:3000,
                        }

                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                commit('saveDocList',success.data.data);
                                resolve(success.data.msg);
                            }
                            reject(success.data.msg);
                        }
                    ).catch(
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject(constant.REDIRECT_LOGIN)
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    )
                }
            )
        }
    },
};
export default module_VideoClass;