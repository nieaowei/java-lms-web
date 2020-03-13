import axios from 'axios'
import constant from "../../constant";

const module_adminDoc = {
    namespaced: true,
    state: {
        docList: [],
    },
    mutations: {
        saveDocList(state, data) {
            state.docList = data;
        },
        addDoc(state,data){
            state.docList.unshift(data)
        }
    },
    actions: {
        getDocList({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/admin/doc/all",
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                commit('saveDocList', success.data.data)
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
        addDoc({commit},item){
            return new Promise(
                (resolve, reject) =>{
                    axios.post(
                        "api/admin/doc/add",
                        {
                            cover:item.cover,
                            path:item.path,
                            name:item.name,
                            duration:item.duration,
                        },
                        {
                            timeout:3000,
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

export default module_adminDoc;