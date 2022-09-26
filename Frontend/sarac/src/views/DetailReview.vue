<template>
  <div>
    {{ review }}
  </div>
</template>

<script>
import { mapActions } from "vuex";
const reviewStore = "reviewStore";

export default {
  name: "detailReview",
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
