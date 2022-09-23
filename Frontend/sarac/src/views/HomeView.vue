<template>
  <div>
    <p>개인리뷰모아보기 home이요~</p>
    <feeds :reviewList="randomReviewList"></feeds>
  </div>
</template>

<script>
// import { mapActions } from "vuex";
// const accountStore = "accountStore";

import axios from "axios";

import feeds from "../components/homeview/feeds.vue";
export default {
  name: "Home",

  components: { feeds },
  data() {
    return {
      randomReviewList: null,
    };
  },

  created() {
    const accessToken = localStorage.getItem("accessToken");
    console.log("homeview created");
    axios({
      url: "api/v1/review/feeds/1",
      method: "GET",
      headers: {
        Authorization: `Bearer ${accessToken}`,
      },
    }).then((res) => {
      console.log(res);
      this.randomReviewList = res.data;
    });
  },
};
</script>
