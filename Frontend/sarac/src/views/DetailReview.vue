<template>
  <div>
    {{ review }}
    <aFeed :review="review"></aFeed>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import aFeed from "@/components/homeview/aFeed.vue";
const reviewStore = "reviewStore";

export default {
  name: "detailReview",
  components: { aFeed },
  props: {
    reviewId: {
      type: Number,
      default: 0,
    },
  },
  data() {
    return {
      review: null,
    };
  },
  methods: {
    ...mapActions(reviewStore, ["getDetailReview"]),
    async getReview(reviewId) {
      this.review = await this.getDetailReview(reviewId);
    },
  },
  created() {
    console.log(this);
    console.log(this.reviewId);
    this.getReview(this.reviewId);
  },
};
</script>

<style></style>
