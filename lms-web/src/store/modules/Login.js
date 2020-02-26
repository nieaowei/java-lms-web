import axios from 'axios'
import constant from "../../constant";

const module_Login = {
    namespaced: true,
    state: {
        loading: false,
        checkUserName: (rule, value, callback) => {
            if (!value) {
                return callback(new Error('账号不能为空'));
            }
            callback()
        },
        checkPass: (rule, value, callback) => {
            if (!value) {
                return callback(new Error('密码不能为空'));
            }
            callback()
        }
    },
    getters: {},
    mutations: {
        alterLoading(state, bool) {
            state.loading = bool;
        }
    },
    actions: {
        login({commit}, loginForm) {
            commit('alterLoading', true)
            return new Promise((resolve, reject) => {
                    axios.post(
                        'api/user/login',
                        {
                            username: loginForm.username,
                            password: loginForm.password
                        },
                        {
                            timeout: 3000
                        }
                    ).then(
                        (successResponse) => {
                            if (successResponse.data['status'] === 200) {
                                resolve(successResponse.data['msg'])
                            }
                            reject(successResponse.data['msg'])
                        }
                    ).catch(
                        () => {
                            //todo  失败处理
                            reject(constant.SERVER_FAIL)
                        }
                    )
                },
            ).finally(
                () => {
                    commit('alterLoading', false)
                }
            )
        },
    },
}

export default module_Login;