// import router from "@/router";
import axios from "axios";

const accountStore = {
  namespaced: true,

  state: {
    user: {},
  },
  getters: {},
  mutations: {
    USER_LOGOUT(state) {
      // 추후 수정
      localStorage.removeItem("accessToken");
      state.user = {};
    },
  },
  actions: {
    async login(context, { accessToken, refreshToken }) {
      console.log(accessToken);
      console.log(refreshToken);
      axios.defaults.baseURL = "http://localhost:8080";

      // const href = window.location.href;
      // const params = new URL(href).searchParams;
      // const accessToken = params.get("accessToken");
      // const refreshToken = params.get("refreshToken");

      sessionStorage.setItem("accessToken", accessToken);
      sessionStorage.setItem("refreshToken", refreshToken);

      try {
        const response = await axios({
          method: "get",
          url: "api/v1/user/me",
          headers: {
            // Authorization: `Bearer ${getState().auth.token}`
            Authorization: `Bearer ${accessToken}`,
          },
        });

        axios.defaults.baseURL = "http://localhost:8080";
        console.log(response.data);
        return response.data;
      } catch (err) {
        console.log("에러에러");
        console.log(accessToken);
        console.log(refreshToken);
      }
    },

    logout({ commit }) {
      const accessToken = localStorage.getItem("accessToken");
      axios({
        url: "api/v1/user/logout",
        method: "POST",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }).then((res) => {
        if (res.data.message == "success") {
          commit("USER_LOGOUT");
        }
      });
    },
  },
};

export default accountStore;
