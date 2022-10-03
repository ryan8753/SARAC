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
        userList: [],
        libraryList: {},
        reviewList: [],
    },
    getters: {

    },
    mutations: {
        GET_USER_INFO(state, payload) {
            state.userInfo = payload;
        },
        GET_USER_LIST(state, payload) {
            // 회원 코드 만들어주는 로직
            for(let i = 0; i < payload.length; i++) {
                let userCode = "";
                for(let j = 0; j < 4 - payload[i].userId.toString().length; j++) {
                    userCode += "0";   
                }
                userCode += payload[i].userId;
                payload[i].userId = userCode;
            }
            
            state.userList = payload;
        },
        GET_LIBRARY(state, payload) {
            state.libraryList = payload;
        },
        GET_REVIEW_LIST(state, payload) {
            state.reviewList = payload;
        },
    },
    actions: {
        getSearchUserInfo({commit}, payload) {
            axios({
                url: `${apiUrl}userinfo`,
                method: "GET",
                headers,
                params: payload,
            }).then((res) => {
                commit("GET_USER_INFO", res.data);
            })
        },
        getSearchUserList({commit}, payload) {
            axios({
                url: `${apiUrl}userlist`,
                method: "GET",
                headers,
                params: payload,
            }).then((res) => {
                commit("GET_USER_LIST", res.data);
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
                router.push("/library");
            })
        },
        getReviewList({commit}, payload) {
            axios({
                url: `${apiUrl}reviewlist`,
                method: "GET",
                headers,
                params: payload,
            }).then((res) => {
                commit("GET_REVIEW_LIST", res.data);           
            });
        },
    },

};

export default myFeedStore;