import axios from "axios";
import constant from "../../constant";

const module_MyClass = {
    namespaced: true,
    state: {
        myDocs: [],
    },
    getters: {},
    mutations: {
        saveMyDocs(state, data) {
            state.myDocs = data
        }
    },
    actions: {
        getMyDocs({commit}) {
            return new Promise((resolve, reject) => {
                axios.get(
                    'api/user/doc/record',
                    {
                        timeout: 3000
                    }
                ).then(
                    (success) => {
                        if (success.data['status'] === 200) {
                            commit('saveMyDocs', success.data['data']);
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
            });
        },
    },
}
export default module_MyClass;