import MyClass from "../../components/home/MyClass";
import MyProfile from "../../components/home/MyProfile";
import DocClass from "../../components/home/DocClass";
import VideoClass from "../../components/home/VideoClass";
import admin from "../../components/admin/admin";

const module_AppIndex = {
    namespaced: true,

    state: {
        loading: false,
        currentView: MyClass,
        menuViews: [MyClass, MyProfile, DocClass,VideoClass,null,admin],
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

    },
}

export default module_AppIndex;