import axios from 'axios'
import constant from "../../constant";

const module_adminVideo = {
    namespaced: true,
    state: {
        videoList: [],
    },
    mutations: {
        saveVideoList(state, data) {
            state.videoList = data;
        },
        addVideo(state, data) {
            state.videoList.unshift(data)
        },
        // eslint-disable-next-line no-unused-vars
        removeVideo(state, index) {
            state.videoList.splice(index, 1)
        },
        changeVideo(state, {vlid, newitem}) {
            console.log('change',vlid)
            console.log('changenew',newitem)
            state.videoList.forEach((item,index)=>{
                if (item.vlid===vlid){
                    state.videoList.splice(index, 1, newitem)
                    return
                }
            })
        }
    },
    actions: {
        getVideoList({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/admin/video/all",
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                commit('saveVideoList', success.data.data)
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
        addVideo({commit}, item) {
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/admin/video/add",
                        {
                            cover: item.cover,
                            path: item.path,
                            name: item.name,
                            duration: item.duration,
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
                        "api/admin/video/getone?vlid=" + data.vlid,
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
        deleteVideo({commit}, data) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/admin/video/deleteone?vlid=" + data.vlid,
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
        updateVideo({commit}, data) {
            console.log(data)
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/admin/video/updateone",
                        {
                            vlid: data.vlid,
                            path: data.path,
                            cover: data.cover,
                            name: data.name,
                            duration: data.duration,
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

export default module_adminVideo;