import axios from "axios";
import constant from "../../constant";

const module_adminUser={
    namespaced: true,
    state:{
        userList:[],
    },
    mutations:{
        saveUserList(state,data){
            state.userList=data;
        },
        changeUserList(state,{item,data}){
            console.log(item)
            console.log(data)
            var index  = state.userList.indexOf(item)
            state.userList.splice(index,1,data)
        },
        removeUser(state,item){
            var index  = state.userList.indexOf(item)
            state.userList.splice(index,1)
        }
    },
    actions:{
        // eslint-disable-next-line no-unused-vars
        getUserList({commit}){
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/admin/getalluser",
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
                            if (fail.response.status===415){
                                reject("您不是管理员")
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    );
                }
            )
        },
        // eslint-disable-next-line no-unused-vars
        resetPd({commit},data){
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/admin/resetpd",

                          data,
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                commit('changeUserList', {item:data, data:success.data.data})
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
                            if (fail.response.status===415){
                                reject("您不是管理员")
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    );
                }
            )
        },
        deleteUser({commit},data){
            return new Promise(
                (resolve, reject) => {
                    axios.post(
                        "api/admin/deleteuser",

                        data,
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data.status === 200) {
                                commit('removeUser', data)
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
                            if (fail.response.status===415){
                                reject("您不是管理员")
                            }
                            reject(constant.SERVER_FAIL)
                        }
                    );
                }
            )
        }
    }
}

export default module_adminUser;
