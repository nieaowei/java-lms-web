import Vue from 'vue'
import Vuex from 'vuex'
import module_MyClass from "./modules/MyClass";
import module_Login from "./modules/Login";
import module_Reigster from "./modules/Register";
import module_MyProfile from "./modules/MyProfile";
import module_AppIndex from "./modules/AppIndex";
import module_DocClass from "./modules/DocClass";
import module_studyDoc from "./modules/studyDoc";
import module_VideoClass from "./modules/VideoClass";
import module_studyVideo from "./modules/studyVideo";
import module_adminDoc from "./modules/adminDoc";
import module_adminVideo from "./modules/adminVideo";
import module_Test from "./modules/Test";
import module_doTest from "./modules/doTest";
import module_adminTest from "./modules/adminTest";

Vue.use(Vuex);

const store = new Vuex.Store(
    {
        modules: {
            MyClass: module_MyClass,
            Login: module_Login,
            Register: module_Reigster,
            MyProfile: module_MyProfile,
            AppIndex: module_AppIndex,
            DocClass: module_DocClass,
            studyDoc: module_studyDoc,
            studyVideo: module_studyVideo,
            VideoClass: module_VideoClass,
            adminDoc: module_adminDoc,
            adminVideo: module_adminVideo,
            adminTest: module_adminTest,
            Test: module_Test,
            doTest: module_doTest,
        },
        state: {},
        mutations: {},
        actions: {
            // eslint-disable-next-line no-unused-vars
            successNotify({commit}, vue, msg) {
                vue.$notify({
                    type: "success",
                    message: msg
                })
            },
            // eslint-disable-next-line no-unused-vars
            failNotify({commit}, vue, msg) {
                vue.$notify({
                    type: "error",
                    message: msg
                })
            }
        }
    },
)

export default store;