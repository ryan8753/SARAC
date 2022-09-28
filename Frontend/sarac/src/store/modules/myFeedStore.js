import axios from "axios";

const apiUrl = "api/v1/myfeed/";
const accessToken = localStorage.getItem("accessToken");
const headers = {
    Authorization: `Bearer ${accessToken}`,
};

const myFeedStore = {
    namespaced: true,

    state: {
        userInfo: {},
        libraryNum: 0,

    },
    getters: {

    },
    mutations: {
        GET_USER_INFO(state, payload) {
            state.userInfo = payload;
        },
        SET_LIBRARY_NUM(state, payload) {
            state.libraryNum = payload;
        }

    },
    actions: {
        getUserInfo({commit}, payload) {
            axios({
                url: `${apiUrl}userinfo`,
                method: "GET",
                headers,
                params: payload,
            }).then((res) => {
                console.log(res.data);
                commit("GET_USER_INFO", res.data);
            })
        },
    },

};

export default myFeedStore;