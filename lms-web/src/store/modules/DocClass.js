import axios from 'axios'
import constant from "../../constant";

const module_DocClass = {
    namespaced: true,

    state: {
        docList: [],
    },
    getters: {},
    mutations: {
        saveDocList(state, data) {
            state.docList = data;
        },

    },
    actions: {
        getDocList({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/doc/all/person",
                        {timeout: 3000}
                    ).then(
                        (success) => {
                            if (success.data['status'] === 200) {
                                commit('saveDocList',success.data['data']);
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
        addDocLearn({commit},dlid_){
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/user/doc/addRecord",
                        {
                            dlid:dlid_
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
export default module_DocClass;