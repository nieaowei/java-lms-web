import axios from 'axios'
import constant from "../../constant";
import Vue from 'vue'


const module_Test = {
    namespaced: true,

    state: {
        testList: {},
    },
    getters: {},
    mutations: {
        saveTestList(state, data) {
            state.testList = data;
        },
        changeTestFlag(state, {key, data}) {
            // state.testList[key].flag = data;
            state.testList.forEach((item) => {
                if (item.tiid === key) {
                    item.flag = data
                    return
                }
            })
        },
        changeTest(state, {key, data}) {
            // state.testList[key] = data
            Vue.$set(state.testList, key, data)
            console.log(state.testList)
        }

    },
    actions: {
        getTestList({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/test/all/person",
                        {timeout: 3000}
                    ).then(
                        (success) => {
                            if (success.data['status'] === 200) {
                                commit('saveTestList', success.data['data']);
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
    },
};
export default module_Test;