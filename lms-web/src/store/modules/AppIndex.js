import MyClass from "../../components/home/MyClass";
import MyProfile from "../../components/home/MyProfile";
import axios from 'axios'
import constant from "../../constant";
import DocClass from "../../components/home/DocClass";
import VideoClass from "../../components/home/VideoClass";

const module_AppIndex = {
    namespaced: true,

    state: {
        loading: false,
        currentView: MyClass,
        menuViews: [MyClass, MyProfile, DocClass,VideoClass],
        classes: [],
        bottom: {
            enable: false,
            percent: 0.0,
            status: "success",
        }
    },
    getters: {},
    mutations: {
        setBottomEnable(state, data) {
            state.bottom.enable = data;
        },
        setBottomPercent(state, data) {
            state.bottom.percent = data;
            if (state.bottom.percent === 100){
                state.status = "success";
            }else {
                state.status = 'success';
            }
        },
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
        getMyProfile({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        '/user/getMyProfile',
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
        // eslint-disable-next-line no-unused-vars
        changeView({commit, dispatch, state, rootState}, view) {
            commit('changeCurrentView', view);
            commit('changeLoading', true);
            if (view === MyClass) {
                return dispatch("MyClass/getMyDocs", {}, {root: true}).finally(
                    () => {
                        commit('changeLoading', false);
                    }
                );
            } else if (view === MyProfile) {
                return dispatch('MyProfile/getProfile', {}, {root: true}).finally(
                    () => {
                        commit('changeLoading', false);
                    }
                );
            } else if (view === DocClass) {
                return dispatch('DocClass/getDocList', {}, {root: true}).finally(
                    () => {
                        commit('changeLoading', false);
                    }
                );
            } else if (view === VideoClass){
                return dispatch('VideoClass/getVideoList', {}, {root: true}).finally(
                    () => {
                        commit('changeLoading', false);
                    }
                );
            }
        }
    },
}

export default module_AppIndex;