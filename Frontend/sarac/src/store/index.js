import Vue from "vue";
import Vuex from "vuex";
// import router from "@/router";
import axios from "axios";

// module 별 Store 불러오기
import accountStore from "@/store/modules/accountStore";
import reviewStore from "@/store/modules/reviewStore";
import searchStore from "@/store/modules/searchStore";
import myFeedStore from "@/store/modules/myFeedStore";
import statisticsStore from "@/store/modules/statisticsStore";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    accountStore: accountStore,
    reviewStore: reviewStore,
    searchStore: searchStore,
    myFeedStore: myFeedStore,
    statisticsStore: statisticsStore,
  },
});

axios.defaults.baseURL = process.env.VUE_APP_API_URL;

export default store;
