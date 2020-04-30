import axios from 'axios'
import constant from "../../constant";


const module_doTest = {
    namespaced: true,

    state: {
        testData: {},
    },
    getters: {
        filterTestData: (state) => {
            // eslint-disable-next-line no-unused-vars
            return state.testData.topics.filter(item => true).map(item => {
                if (item.type === 1){
                    // var tempanswer = item.answer.sort()
                    // var res = ''
                    // tempanswer=res
                    return {answer: item.answer.sort().toString(), tcid: item.tcid}
                }
                return {answer: item.answer, tcid: item.tcid}
            })
        },
    },
    mutations: {
        saveTestData(state, data) {
            state.testData = data;
        },
        changeAnswer(state, data) {
            state.testData.topics.answer = data
        }

    },
    actions: {
        // eslint-disable-next-line no-unused-vars
        getTestData({commit, state},tiid) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/test/getone?tiid=" + tiid,
                        {timeout: 3000}
                    ).then(
                        (success) => {
                            if (success.data['status'] === 200) {
                                resolve(success.data);
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
        postTestData({commit, state, getters}, data) {
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/user/test/postone",
                        {
                            tiid: state.testData.tiid,
                            topics: getters.filterTestData,
                        },
                    ).then(
                        (success) => {
                            if (success.data['status'] === 200) {
                                // commit('saveTestData', success.data['data']);
                                resolve(success.data);
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
export default module_doTest;
