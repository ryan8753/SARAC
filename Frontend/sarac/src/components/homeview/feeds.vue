<template>
  <div>
    <!-- <p>개인리뷰모아보기 feeds</p> -->
    <!-- {{ this.reviewList }} -->
    <div class="wrapper">
      <div
        class="card"
        v-for="(review, index) in reviewList"
        :key="index + 'c'"
      >
        <div>
          <aFeed :review="review"></aFeed>
        </div>
        <br />
      </div>
    </div>
  </div>
</template>

<script>
import aFeed from "./aFeed.vue";
export default {
  components: { aFeed },
  props: ["reviewList"],
  data() {
    return {};
  },

  created() {},
};
</script>
