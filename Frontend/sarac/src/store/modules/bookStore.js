// import router from "@/router";
// import axios from "axios";

import axios from "axios";

// import accountStore from "@/store/modules/accountStore";
// const userId = accountStore.state.user.userId;

const bookStore = {
  namespaced: true,
  state: {
    book: {
      libraryType: null,
    },
    reviewList: [],
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
    getBookDetail({ commit }, bookId) {
      axios({
        url: `api/v1/book/detail/${bookId}`,
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
    editReadStatus({ commit, dispatch }, { bookId, newStatus, userId }) {
      axios({
        url: "api/v1/book/detail",
        method: "post",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
        data: {
          isbn: `${bookId}`,
          userId: userId,
          libraryType: newStatus,
        },
      }).then((res) => {
        if (res.data.message === "success") {
          commit("EDIT_READ_STATUS", newStatus);
          dispatch("myFeedStore/getSearchUserInfo", { userId: userId }, { root: true });
        }
      });
    },
    deleteReadStatus(context, bookId) {
      axios({
        url: `api/v1/book/detail/?isbn=${bookId}`,
        method: "delete",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
      }).then((res) => {
        if (res.data.message === "success") {
          context.commit("EDIT_READ_STATUS", "");
          context.dispatch("myFeedStore/getSearchUserInfo", {userId: context.rootState.accountStore.user.userId}, { root: true });
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
        if (res.status === 200) {
          commit("SET_REVIEW_LIST", res.data);
          return res.data;
        }
      });
    },
  },
};

export default bookStore;
