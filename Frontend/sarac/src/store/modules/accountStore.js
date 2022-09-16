import router from "@/router";
import axios from "axios";

const accountStore = {
  namespaced: true,

  state: {
    user: {},
  },
  getters: {},
  mutations: {
    SET_USER_INFO(state, userInfo){
      state.user = userInfo
      console.log(userInfo)
    },
    USER_LOGOUT(state) {
      // 추후 수정
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
      state.user = {};
    },
  },
  actions: {
    async getUserInfo({commit}, { accessToken, refreshToken }) {
      axios.defaults.baseURL = "http://localhost:8080";     
      try {
        const response = await axios({
          method: "get",
          url: "api/v1/user/me",
          headers: {
            // Authorization: `Bearer ${getState().auth.token}`
            Authorization: `Bearer ${accessToken}`,
          },
          });
          commit('SET_USER_INFO', response.data)
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
          router.go();
        }
      });
    },

    signout({commit}) {
      const accessToken = localStorage.getItem("accessToken");
      axios({
        url: "api/v1/user",
        method: "delete",
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      }).then((res) => {
        if (res.data.message == "success") {
          commit("USER_LOGOUT");
          router.go();
          alert('회원탈퇴성공')
        }
      });
    }
  },
};

export default accountStore;
