import axios from "axios";

const statisticsStore = {

    namespaced: true,
    state: {},
    getters: {},
    mutations: {},
    actions: {
        async getStatistics(context, userId) {
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
      
    }
};

export default statisticsStore;