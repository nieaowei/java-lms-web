import axios from "axios";
import constant from "../../constant";
// import Vue from "vue";

const module_MyClass = {
    namespaced: true,
    state: {
        myDocs: {},
        myVideos: {},
        myTests: {}
    },
    getters: {},
    mutations: {
        saveMyDocs(state, data) {
            state.myDocs = data
        },
        saveMyVideos(state, data) {
            state.myVideos = data
        },
        addMyDoc(state, data) {
            state.myDocs.unshift(data)
        },
        addMyVideo(state, data) {
            state.myVideos.unshift(data)
        },
        changeMyDocPercent(state, {key, data}) {
            state.myDocs.forEach((item)=>{
                if (item.dlid === key){
                    item.percent = data
                    return
                }
            })
            // state.myDocs[key].percent = data
        },
        changeMyVideoPercent(state, {key, data}) {
            state.myVideos[key].percent = data
        },
        saveMyTests(state, data) {
            state.myTests = data;
        },
        addMyTest(state,item){
            state.myTests.unshift(item)
        }
    },
    actions: {
        // eslint-disable-next-line no-unused-vars
        getMyDocs({commit, state}) {
            return new Promise((resolve, reject) => {
                axios.get(
                    'api/user/doc/record',
                    {
                        timeout: 3000
                    }
                ).then(
                    (success) => {
                        if (success.data.status === 200) {
                            commit('saveMyDocs', success.data.data);
                            resolve(success.data.msg)
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
            });
        },
        // eslint-disable-next-line no-unused-vars
        updateDuration({commit, state}, _dlid) {
            console.log(state.myDocs)
            return new Promise((resolve, reject) => {
                axios.post(
                    "api/user/doc/update",
                    {
                        dlid: _dlid
                    }
                ).then(
                    (success) => {
                        console.log('suc',success)

                        if (success.data.status === 200) {
                            //更新本地数据
                            commit('changeMyDocPercent', {key: _dlid, data: success.data.data.percent})
                            resolve(success.data.data)
                        }
                        reject(success.data.msg)
                    }
                ).catch(
                    // eslint-disable-next-line no-unused-vars
                    (fail) => {
                        console.log('fail ',fail)

                        if (fail.response.status === 302) {
                            reject(constant.REDIRECT_LOGIN)
                        }
                        reject(constant.SERVER_FAIL)
                    }
                );
            })
        },
        getMyVideos({commit}) {
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
        updateVideoDuration({commit}, _vlid) {
            return new Promise((resolve, reject) => {
                axios.post(
                    "api/user/video/update",
                    {
                        vlid: _vlid
                    }
                ).then(
                    (success) => {
                        if (success.data.status === 200) {
                            commit('changeMyVideoPercent', {key: _vlid, data: success.data.data.percent})
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
        },
        getMyTests({commit}){
            return new Promise((resolve, reject) => {
                axios.get(
                    'api/user/test/record',
                    {
                        timeout: 3000
                    }
                ).then(
                    (success) => {
                        if (success.data.status === 200) {
                            commit('saveMyTests', success.data.data);
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
        }
    },
}
export default module_MyClass;