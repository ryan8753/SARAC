import axios from "axios";
import router from "@/router";

const apiUrl = "api/v1/myfeed/";
const accessToken = localStorage.getItem("accessToken");
const headers = {
    Authorization: `Bearer ${accessToken}`,
};

const myFeedStore = {
    namespaced: true,

    state: {
        userInfo: {},
        libraryList: {},

    },
    getters: {

    },
    mutations: {
        GET_USER_INFO(state, payload) {
            state.userInfo = payload;
        },
        SET_LIBRARY_NUM(state, payload) {
            state.libraryNum = payload;
        },
        GET_LIBRARY(state, payload) {
            state.libraryList = payload;
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
                commit("GET_USER_INFO", res.data);
            })
        },
        getLibrary({commit}, payload) {
            axios({
                url: `${apiUrl}library`,
                method: "GET",
                headers,
                params: payload,
            }).then((res) => {
                commit("GET_LIBRARY", res.data);
                router.push("/library/" + payload.userId);
            })
        }
    },

};

export default myFeedStore;