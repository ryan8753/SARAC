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
        console.log(res);
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },
    registReview(context,review) {
      context;
      const formData = new FormData();
      // formData.append('review', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
      // formData.append("files", photoFile.files[0]);
      const accessToken = localStorage.getItem("accessToken");
      const response = axios({
        url: "api/v1/review",
        method: "POST",
        headers: {
          Authorization: `Bearer ${accessToken}`,
          'Content-Type': 'multipart/form-data',
        },
        data:{
          formData,
        },

        
      }).then((res) => {
        console.log(res);
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },
    async updateReview() {
      const formData = new FormData();
      // formData.append('review', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
      // formData.append("files", photoFile.files[0]);
      const accessToken = localStorage.getItem("accessToken");
      const response = await axios({
        url: "api/v1/review/update",
        method: "PUT",
        headers: {
          Authorization: `Bearer ${accessToken}`,
          'Content-Type': 'multipart/form-data',
        },
        data:{
          formData,
        },

        
      }).then((res) => {
        console.log(res);
        // this.randomReviewList = res.data;
        return res.data;
      });
      return response;
    },
  },
};

export default reviewStore;
