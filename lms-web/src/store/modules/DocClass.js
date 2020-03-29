import axios from 'axios'
import constant from "../../constant";
import Vue from 'vue'


const module_DocClass = {
    namespaced: true,

    state: {
        docList: {},
    },
    getters: {},
    mutations: {
        saveDocList(state, data) {
            state.docList = data;
        },
        changeDocFlag(state, {key, data}) {
            state.docList[key].flag = data;
        },
        changeDoc(state, {key, data}) {
            // state.docList[key] = data
            Vue.$set(state.docList, key, data)
        },
        changeDocCount(state,key){
            state.docList[key].count++;
        }

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
                                commit('saveDocList', success.data['data']);
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
        addDocLearn({commit, state}, key) {
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/user/doc/addRecord",
                        {
                            dlid: state.docList[key].dlid
                        },
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                commit('changeDocFlag', {key: key, data: true})
                                commit('changeDocCount',key)
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
export default module_DocClass;
