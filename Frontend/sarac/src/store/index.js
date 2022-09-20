import Vue from "vue";
import Vuex from "vuex";
// import router from "@/router";
// import axios from "axios";

// module 별 Store 불러오기
import accountStore from "@/store/modules/accountStore";

Vue.use(Vuex);

const store = new Vuex.Store({
  modules: {
    accountStore: accountStore,
  },
});

export default store;
