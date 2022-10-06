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
import bookStore from "@/store/modules/bookStore";
import mapStore from "@/store/modules/mapStore";
import likeStore from "@/store/modules/likeStore";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    accountStore: accountStore,
    reviewStore: reviewStore,
    searchStore: searchStore,
    myFeedStore: myFeedStore,
    bookStore: bookStore,
    statisticsStore: statisticsStore,
    mapStore: mapStore,
    likeStore: likeStore,
  },
});

axios.defaults.baseURL = process.env.VUE_APP_API_URL;

export default store;
