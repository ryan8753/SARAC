import axios from "axios";

const apiUrl = "api/v1/myfeed/";
const accessToken = localStorage.getItem("accessToken");

const myFeedStore = {
    namespaced: true,

    state: {
        userInfo: {},

    },
    getters: {

    },
    mutations: {
        GET_USER_INFO(state, payload) {
            state.userInfo = payload;
        },

    },
    actions: {
        getUserInfo({commit}, payload) {
            axios({
                url: `${apiUrl}userinfo`,
                method: "GET",
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                  },
                params: payload,
            }).then((res) => {
                console.log(res.data);
                commit("GET_USER_INFO", res.data);
            })
        },
    },

};

export default myFeedStore;