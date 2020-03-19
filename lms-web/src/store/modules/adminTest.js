import axios from 'axios'
import constant from "../../constant";

const module_adminTest = {
    namespaced: true,
    state: {
        testList: [],
    },
    mutations: {
        saveTestList(state, data) {
            state.testList = data;
        },
        addTest(state, data) {
            state.testList.unshift(data)
        },
        // eslint-disable-next-line no-unused-vars
        removeTest(state, index) {
            state.testList.splice(index, 1)
        },
        changeTest(state, {tiid, newitem}) {
            state.testList.forEach((item,index)=>{
                if (item.tiid===tiid){
                    state.testList.splice(index, 1, newitem)
                    return
                }
            })
        }
    },
    actions: {
        getTestList({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/admin/test/all",
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                commit('saveTestList', success.data.data)
                                resolve(success.data.msg)
                            }
                            reject(success.data.msg)
                        }
                    ).catch(
                        (fail) => {
                            reject(fail)
                        }
                    )
                }
            )
        },
        // eslint-disable-next-line no-unused-vars
        addTest({commit}, item) {
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/admin/test/add",
                        {
                            title: item.title,
                            cover: item.cover,
                            topics:item.topics,
                        },
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                resolve(success.data)
                            }
                            reject(success.data.msg)
                        }
                    ).catch(
                        // eslint-disable-next-line no-unused-vars
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject(constant.REDIRECT_LOGIN)
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    );
                }
            )
        },
        // eslint-disable-next-line no-unused-vars
        getDetail({commit}, data) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/admin/test/getone?tiid=" + data.tiid,
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                resolve(success.data)
                            }
                            reject(success.data.msg)
                        }
                    ).catch(
                        // eslint-disable-next-line no-unused-vars
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject(constant.REDIRECT_LOGIN)
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    );
                }
            )
        },
        // eslint-disable-next-line no-unused-vars
        deleteTest({commit}, data) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/admin/test/deleteone?tiid=" + data.tiid,
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                resolve(success.data)
                            }
                            reject(success.data.msg)
                        }
                    ).catch(
                        // eslint-disable-next-line no-unused-vars
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject(constant.REDIRECT_LOGIN)
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    );
                }
            )
        },
        // eslint-disable-next-line no-unused-vars
        updateTest({commit}, data) {
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/admin/test/updateone",
                        {
                            tiid: data.tiid,
                            cover: data.cover,
                            title: data.title,
                            topics: data.topics,
                            createtime:data.createtime,
                            updatetime:data.updatetime,
                        },
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                resolve(success.data)
                            }
                            reject(success.data.msg)
                        }
                    ).catch(
                        // eslint-disable-next-line no-unused-vars
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject(constant.REDIRECT_LOGIN)
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    );
                }
            )
        }
    }
}

export default module_adminTest;