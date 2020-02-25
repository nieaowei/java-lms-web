import MyClass from "../../components/home/MyClass";
import MyProfile from "../../components/home/MyProfile";
import axios from 'axios'
const module_AppIndex = {
    namespaced:true,

    state:{
        loading:false,
        currentView:MyClass,
        menuViews: [MyClass,MyProfile],
    },
    getters:{

    },
    mutations:{
        changeCurrentView(state,view){
            state.currentView=view;
        },
        changeLoading(state,bool){
            state.loading=bool;
        }
    },
    actions:{
        getMyClass({commit}){
            return new Promise(()=>{
                axios.get('/user/learnrecord',
                    {timeout:3000}
                ).then((success)=>{
                    console.log('响应成功')
                    if (success.data['status']===200){
                    }
                    // eslint-disable-next-line no-unused-vars
                }).catch((fail)=>{

                });
            }).finally(
                ()=>{
                    commit('changeLoading',false);
                }
            );
        },
        getMyProfile({commit}){
            return new Promise(()=>{
                axios.get('/user/learnrecord',
                    {timeout:3000}
                ).then((success)=>{
                    if (success.data['status']===200){

                    }
                    // eslint-disable-next-line no-unused-vars
                }).catch((fail)=>{
                    commit('changeLoading',false);
                });
            });
        },
        changeView({dispatch,commit},view){
            commit('changeCurrentView',view);
            commit('changeLoading',true);
            if (view===MyClass){
                return dispatch('getMyClass').then(()=>{
                    commit('changeLoading',false);
                },()=>{
                    commit('changeLoading',false);
                })
            }else if (view===MyProfile) {
                return dispatch('getMyProfile').then(()=>{
                    commit('changeLoading',false);
                },()=>{
                    commit('changeLoading',false);
                })
            }
        }
    },
}

export default module_AppIndex;