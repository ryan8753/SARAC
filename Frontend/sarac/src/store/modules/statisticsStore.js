import axios from "axios";

const statisticsStore = {

    namespaced: true,
    state: {},
    getters: {},
    mutations: {},
    actions: {
        async getOtherStatistics(context, userId) {
            context;
            const accessToken = localStorage.getItem("accessToken");
            const response = await axios({
              url: `api/v1/statistics/other/${userId}`,
              method: "GET",
              headers: {
                Authorization: `Bearer ${accessToken}`,
              },
            }).then((res) => {
              return res.data;
            });
            return response;
          },
          async getMyStatistics(context) {
            context;
            const accessToken = localStorage.getItem("accessToken");
            const response = await axios({
              url: `api/v1/statistics/me`,
              method: "GET",
              headers: {
                Authorization: `Bearer ${accessToken}`,
              },
            }).then((res) => {
              return res.data;
            });
            return response;
      },
      async getMyHashtag(context) {
        context;
        const accessToken = localStorage.getItem("accessToken");
        const response = await axios({
          url: `api/v1/statistics/hashtag`,
          method: "GET",
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }).then((res) => {
          return res.data
        });
        return response;
    
      },
      async getOtherHashtag(context,userId) {
        context;
        const accessToken = localStorage.getItem("accessToken");
        const response = await axios({
          url: `api/v1/statistics/hashtag/${userId}`,
          method: "GET",
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        }).then((res) => {
          return res.data
        });
        return response;
    
      },
      
    }
};

export default statisticsStore;