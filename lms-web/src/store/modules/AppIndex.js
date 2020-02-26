import MyClass from "../../components/home/MyClass";
import MyProfile from "../../components/home/MyProfile";
import axios from 'axios'
import constant from "../../constant";

const module_AppIndex = {
    namespaced: true,

    state: {
        loading: false,
        currentView: MyClass,
        menuViews: [MyClass, MyProfile],
        classes: [],
    },
    getters: {},
    mutations: {
        saveClasses(state, data) {
            state.classes = data;
        },
        changeCurrentView(state, view) {
            state.currentView = view;
        },
        changeLoading(state, bool) {
            state.loading = bool;
        }
    },
    actions: {
        getMyClass({commit}) {
            return new Promise((resolve, reject) => {
                axios.get(
                    'api/user/doc/record',
                    {
                        timeout: 3000
                    }
                ).then(
                    (success) => {
                        if (success.data['status'] === 200) {
                            commit('saveClasses', success.data['data']);
                            resolve(success.data['msg'])
                        }
                        reject(success.data['msg'])
                    }
                ).catch(
                    (fail) => {
                        if (fail.response.status === 302) {
                            reject(constant.REDIRECT_LOGIN)
                        }
                        reject(constant.SERVER_FAIL)
                    }
                );
            }).finally(
                () => {
                    commit('changeLoading', false);
                }
            );
        },
        getMyProfile({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get('/user/getMyProfile',
                        {timeout: 3000}
                    ).then(
                        (success) => {
                            // eslint-disable-next-line no-empty
                            if (success.data['status'] === 200) {
                                resolve()
                            }
                            // eslint-disable-next-line no-unused-vars
                        }
                    ).catch(
                        (fail) => {
                            if (fail.response.status === 302) {
                                reject(constant.REDIRECT_LOGIN)
                            }
                            reject()
                        }
                    );
                }
            ).finally(
                () => {
                    commit('changeLoading', false);
                }
            );
        },
        changeView({dispatch, commit}, view) {
            commit('changeCurrentView', view);
            commit('changeLoading', true);
            if (view === MyClass) {
                return dispatch('getMyClass');
            } else if (view === MyProfile) {
                return dispatch('getMyProfile');
            }
        }
    },
}

export default module_AppIndex;