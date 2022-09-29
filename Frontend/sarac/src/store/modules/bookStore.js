// import router from "@/router";
// import axios from "axios";

import axios from "axios";

const bookStore = {
  namespaced: true,
  state: {
    book: {
      libraryType: "read",
    },
  },
  getters: {},
  mutations: {
    SET_BOOK_INFO(state, bookInfo) {
      state.book = bookInfo;
    },
  },
  actions: {
    getBookDetail({ commit }, bookId) {
      const userId = 2430262127;
      axios({
        url: `api/v1/book/detail?isbn=${bookId}&userId=${userId}`,
        method: "get",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
        .then((res) => {
          console.log(res);
          commit("SET_BOOK_INFO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    editReadStatus(_, bookId) {
      const userId = "보류보류";
      axios({
        url: `api/v1/book/detail?isbn=${bookId}&userId=${userId}`,
        method: "post",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      }).then((res) => {
        if (res.message === "success") {
          // commit('SET_BOOK_INFO',res.data)
        }
      });
    },
    deleteReadStatus(_, bookId) {
      const userId = "보류보류";
      axios({
        url: `api/v1/book/detail?isbn=${bookId}&userId=${userId}`,
        method: "delete",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      }).then((res) => {
        if (res.message === "success") {
          // commit('SET_BOOK_INFO',res.data)
        }
      });
    },
  },
};

export default bookStore;
