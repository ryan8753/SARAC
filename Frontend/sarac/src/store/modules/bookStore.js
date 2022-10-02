// import router from "@/router";
// import axios from "axios";

import axios from "axios";

// import accountStore from "@/store/modules/accountStore";
// const userId = accountStore.state.user.userId;

const bookStore = {
  namespaced: true,
  state: {
    book: {},
  },
  getters: {
    bookReadStatus: (state) => {
      return state.book.libraryType;
    },
  },
  mutations: {
    SET_BOOK_INFO(state, bookInfo) {
      state.book = bookInfo;
    },
    SET_REVIEW_LIST(state, reviewList) {
      state.reviewList = reviewList;
    },
    EDIT_READ_STATUS(state, readStatus) {
      state.book.libraryType = readStatus;
    },
  },
  actions: {
    getBookDetail({ commit }, { bookId, userId }) {
      axios({
        url: `api/v1/book/detail?isbn=${bookId}&userId=${userId}`,
        method: "get",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      })
        .then((res) => {
          commit("SET_BOOK_INFO", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    editReadStatus({ commit, rootState }, { bookId, newStatus }) {
      const userId = rootState.accountStore.user.userId;
      axios({
        url: `api/v1/book/detail?isbn=${bookId}&userId=${userId}`,
        method: "post",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
        data: {
          libraryType: newStatus,
        },
      }).then((res) => {
        if (res.message === "success") {
          commit("EDIT_READ_STATUS", res.data);
        }
      });
    },
    deleteReadStatus(_, bookId) {
      const userId = "보류";
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
    getReviewList({ commit }, bookId) {
      axios({
        url: `api/v1/review/book/${bookId}`,
        method: "get",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      }).then((res) => {
        console.log(res);
        if (res.status === 200) {
          commit("SET_REVIEW_LIST", res.data);
        }
      });
    },
  },
};

export default bookStore;
