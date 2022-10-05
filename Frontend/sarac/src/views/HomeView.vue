<template>
  <div class="home-container">
    <!-- {{ randomReviewList }} -->
    <!-- <p>개인리뷰모아보기 home이요~</p> -->
    <feeds :reviewList="randomReviewList" v-on:toggleLike="getFeeds()"></feeds>
  </div>
</template>

<script>
import { mapActions } from "vuex";
const reviewStore = "reviewStore";

import feeds from "../components/homeview/feeds.vue";
export default {
  name: "Home",

  components: { feeds },

  methods: {
    ...mapActions(reviewStore, ["getRandomFeeds"]),

    async getFeeds() {
      this.randomReviewList = await this.getRandomFeeds();
    },
  },
  data() {
    return {
      randomReviewList: null,
    };
  },

  created() {
    this.getFeeds();
  },
};
</script>

<style scoped>
.home-container {
  padding: 5%;
}
</style>
