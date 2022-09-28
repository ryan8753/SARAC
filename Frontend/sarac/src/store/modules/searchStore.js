import axios from "axios";

const searchStore = {
  namespaced: true,

  state: {
    searchResults: [],
    isSearched: false,
  },
  getters: {
    searchResults: (state) => {
      return state.searchResults;
    },
    isSearched: (state) => {
      return state.isSearched;
    },
  },
  mutations: {
    SET_BOOK_RESULT(state, searchResults) {
      state.searchResults = searchResults;
      console.log(state.searchResults);
    },
    SET_TEXT_TRUE(state) {
      state.isSearched = true;
    },
    SET_TEXT_FALSE(state) {
      state.isSearched = false;
    },
  },
  actions: {
    getBestBook({ commit }) {
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
