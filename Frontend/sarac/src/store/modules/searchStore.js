import axios from "axios";

const searchStore = {
  namespaced: true,

  state: {
    searchResults: [],
  },
  getters: {
    searchResults: (state) => {
      return state.searchResults;
    },
  },
  mutations: {
    SET_BOOK_RESULT(state, searchResults) {
      state.searchResults = searchResults;
      console.log(state.searchResults);
    },
  },
  actions: {
    getBestBook({ commit }) {
      axios.defaults.baseURL = "http://localhost:8080";
      axios({
        method: "get",
        url: "api/v1/book/search/best",
      }).then((response) => {
        commit("SET_BOOK_RESULT", response.data);
      });
    },

    getBookResults({ commit }, keyword) {
      axios({
        url: "api/v1/book/search?keyword=" + keyword,
        method: "GET",
      }).then((response) => {
        commit("SET_BOOK_RESULT", response.data);
      });
    },
  },
};

export default searchStore;
