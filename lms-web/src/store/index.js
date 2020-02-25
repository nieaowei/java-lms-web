import Vue from 'vue'
import Vuex from 'vuex'
import module_MyClass from "./modules/MyClass";
import module_Login from "./modules/Login";
import module_Reigster from "./modules/Register";
import module_MyProfile from "./modules/MyProfile";
import module_AppIndex from "./modules/AppIndex";

Vue.use(Vuex);

const store = new Vuex.Store(
    {
        modules: {
            Myclass: module_MyClass,
            Login: module_Login,
            Register: module_Reigster,
            MyProfile: module_MyProfile,
            AppIndex: module_AppIndex,
        }
    }
)

export default store;