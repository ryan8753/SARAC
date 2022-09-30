// import router from "@/router";
import axios from "axios";

const reviewStore = {
  namespaced: true,
  state: {
    book: {
      isbn: "",
      bookTitle: "",
      bookImgUrl: "",
    },
  },
  getters: {
    book: (state) => {
      return state.book;
    },
  },
  mutations: {
    SET_BOOK_DATA(state, book) {
      state.book.isbn = book.isbn;
      state.book.bookTitle = book.bookTitle;
      state.book.bookImgUrl = book.bookImgUrl;
    },
    CLEAR_BOOK_DATA(state) {
      state.book.isbn = "";
      state.book.bookTitle = "";
      state.book.bookImgUrl = "";
    },
  },
  actions: {
    async getRandomFeeds() {
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios({
        url: "api/v1/review/feeds",
        method: "GET",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }).then((res) => {
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },

    async getDetailReview(commit, reviewId) {
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios({
        url: `api/v1/review/detail/${reviewId}`,
        method: "GET",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }).then((res) => {
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },

    async createCommentApi(commit, inputs) {
      console.log(inputs);
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios({
        url: `api/v1/review/comment`,
        method: "POST",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
        data: inputs,
      }).then((res) => {
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },

    async deleteCommentApi(commit, inputs) {
      console.log(inputs);
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios({
        url: `api/v1/review/comment/${inputs}`,
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }).then((res) => {
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },
    async registReview(context, { review, files }) {
      context;
      console.log(review);
      console.log(files);
      const formData = new FormData();
      formData.append(
        "review",
        new Blob([JSON.stringify(review)], { type: "application/json" })
      );

      for (let i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
      }

      const accessToken = localStorage.getItem("accessToken");
      const response = await axios({
        url: "api/v1/review",
        method: "POST",
        headers: {
          Authorization: `Bearer ${accessToken}`,
          // 'Content-Type': 'multipart/form-data',
        },
        data: formData,
      }).then((res) => {
        console.log(res);
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },
    async updateReview(context, { review, files,reviewId}) {
      console.log("*************");
      console.log(review)
      console.log(files);
      context;
      const formData = new FormData();
      formData.append(
        "review",
        new Blob([JSON.stringify(review)], { type: "application/json" })
      );
      formData.append(
        "id",
        new Blob([JSON.stringify(reviewId)], { type: "application/json" })
      );

      for (let i = 0; i < files.length; i++) {
        formData.append("files", files[i]);
      }
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios({
        url: "api/v1/review/update",
        method: "PUT",
        
        headers: {
          Authorization: `Bearer ${accessToken}`,
          // 'Content-Type': 'multipart/form-data',
        },
        data: 
          formData,
        
      }).then((res) => {
        console.log(res);
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },
    saveBookData({ commit }, book) {
      commit("SET_BOOK_DATA", book);
    },
  },
};

export default reviewStore;
