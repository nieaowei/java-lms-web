import axios from "axios"
import constant from "../../constant";

const module_MyProfile = {
    namespaced: true,

    state: {
        profile: {},
    },
    getters: {},
    mutations: {
        saveProfile(state, data) {
            state.profile = data;
        }
    },
    actions: {
        getProfile({commit}) {
            return new Promise(
                (resolve, reject) => {
                    axios.get(
                        "api/user/profile",
                        {
                            timeout: 3000,
                        }
                    ).then(
                        (success) => {
                            if (success.data["status"] === 200) {
                                commit("saveProfile",success.data['data']);
                                resolve(success.data['msg']);
                            }
                            reject(success.data['msg']);
                        }
                    ).catch(
                        (fail)=>{
                            if (fail.response.status===302){
                                reject(constant.REDIRECT_LOGIN);
                            }
                            reject(constant.SERVER_FAIL);
                        }
                    )
                }
            );
        }
    },
};
export default module_MyProfile;