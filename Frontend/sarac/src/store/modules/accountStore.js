import router from "@/router";
import axios from "axios";

const accountStore = {
  namespaced: true,

  state: {
    user: null,
  },
  getters: {
    isLoggedIn: (state) => {
      return !!state.user;
    },
  },
  mutations: {
    SET_USER_INFO(state, userInfo) {
      state.user = userInfo;
    },
    EDIT_USER_INFO(state, info) {
      state.user = {
        ...state.user,
        ...info,
      };
    },
    USER_LOGOUT(state) {
      // 추후 수정
      localStorage.removeItem("accessToken");
      localStorage.removeItem("refreshToken");
      state.user = null;
    },
  },
  actions: {
    async getUserInfo({ commit }, { accessToken, refreshToken }) {
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
        commit("SET_USER_INFO", response.data);
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
        method: "post",
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

    signout({ commit }) {
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
          alert("회원탈퇴성공");
        }
      });
    },
    editImg({ commit }, { file }) {
      const formData = new FormData();
      formData.append("file", file);
      console.log("111", file);
      console.log(formData.keyValue);
      const accessToken = localStorage.getItem("accessToken");
      axios({
        url: "api/v1/user/upload",
        method: "post",
        headers: {
          "content-type": "multipart/form-data",
          Authorization: `Bearer ${accessToken}`,
        },
        data: formData,
      })
        .then((res) => {
          console.log(res);
          if (res.status == 200) {
            commit("EDIT_USER_INFO", res.data);
            alert("프로필사진이 수정되었습니다");
          }
        })
        .catch((err) => {
          console.log(err);
          alert("이미지업로드에러");
        });
    },
    editUserNickName({ commit }, payload) {
      const accessToken = localStorage.getItem("accessToken");
      axios({
        url: "api/v1/user",
        method: "patch",
        headers: { Authorization: `Bearer ${accessToken}` },
        data: payload,
      }).then((res) => {
        if (res.status == 200) {
          commit("EDIT_USER_INFO", payload);
          alert("회원정보가 수정되었습니다.");
        }
      });
    },
    editOpenInfo({ state }) {
      console.log("실행");
      console.log(state.user.reviewOpen);
      const data = {
        isLibraryOpen: state.user.libraryOpen,
        isReviewOpen: state.user.reviewOpen,
      };
      axios({
        url: "api/v1/user",
        method: "patch",
        headers: {
          Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
        },
        data: data,
      }).then((res) => {
        if (res.status == 200) {
          alert("회원정보가 수정되었습니다.");
        }
      });
    },
  },
};

export default accountStore;
