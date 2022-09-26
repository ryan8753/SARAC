// import router from "@/router";
import axios from "axios";
const reviewStore = {
  namespaced: true,
  state: {},
  getters: {},
  mutations: {},
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
  },
};

export default reviewStore;
