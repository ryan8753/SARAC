import axios from "axios";

const searchStore = {
  namespaced: true,

  state: {
    searchResults: [],
    wordCloud: [],
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
    },
    SET_TEXT_TRUE(state) {
      state.isSearched = true;
    },
    SET_TEXT_FALSE(state) {
      state.isSearched = false;
    },
    SET_WORD_CLOUD(state, wordCloud) {
      state.wordCloud = wordCloud;
    }
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

    getBookResults({ commit }, payload) {
      axios({
        url: "api/v1/book/search?keyword=" + payload.keyword + "&page=" + payload.page + "&type=" + payload.type,
        method: "GET",
      }).then((response) => {
        commit("SET_BOOK_RESULT", response.data);
      });
    },

    getWordCloud({commit}) {
      axios({
        url: "api/v1/book/keyword",
        method: "GET",
      }).then((response) => {
        console.log(response.data);
        commit("SET_WORD_CLOUD", response.data.keywords);
      });
    }
  },
};

export default searchStore;
