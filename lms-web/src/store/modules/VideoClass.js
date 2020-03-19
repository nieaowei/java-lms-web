import axios from 'axios'
import constant from "../../constant";

const module_VideoClass = {
    namespaced: true,

    state: {
        videoList: {},
    },
    getters: {},
    mutations: {
        saveVideoList(state, data) {
            state.videoList = data;
        },
        changeVideoFlag(state, {key, data}) {
            state.videoList[key].flag = data;
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
                                commit('saveVideoList', success.data['data']);
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
        // eslint-disable-next-line no-unused-vars
        addVideoLearn({commit, state}, key) {
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/user/video/addRecord",
                        {
                            vlid: state.videoList[key].vlid
                        },
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                // commit('saveVideoList',success.data.data);
                                commit('changeVideoFlag', {key: key, data: true})
                                resolve(success.data);
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