<template>
  <div>
    <!-- <p>개인리뷰모아보기 home이요~</p> -->
    <feeds :reviewList="randomReviewList"></feeds>
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
      // console.log(this.$store.state.accountStore.user.kakaoId);
      // ID를 구해오는것 고민 필요
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

<style scoped></style>
