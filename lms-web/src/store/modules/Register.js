import axios from 'axios'
import constant from "../../constant";

const module_Reigster = {
    namespaced: true,

    state: {
        loading: false,

    },
    getters: {},
    mutations: {
        changeLoading(state, bool) {
            state.loading = bool;
        }
    },
    actions: {
        register({commit}, registerForm) {
            commit('changeLoading', true)
            return new Promise((resolve, reject) => {
                axios.post(
                    'api/user/register',
                    {
                        username: registerForm.username,
                        password: registerForm.password,
                        phonenum: registerForm.phonenum,
                        admin: registerForm.admin,
                    },
                    {
                        timeout: 3000,
                    }
                ).then(
                    (success) => {
                        if (success.data['status'] === 200) {
                            resolve(success.data['msg'])
                        }
                        reject(success.data['msg'])
                    }
                ).catch(
                    () => {
                        reject(constant.SERVER_FAIL)
                    }
                )
            }).finally(
                () => {
                    commit('changeLoading', false)
                }
            );
        }
    },
}

export default module_Reigster;
