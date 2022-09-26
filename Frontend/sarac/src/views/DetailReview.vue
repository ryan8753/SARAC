<template>
  <div>
    {{ review }}
    <!-- 리뷰 -->
    <aFeed :review="review"></aFeed><br />
    <!-- hashtag -->
    <v-row class="mr-auto">
      <v-col v-for="(hashtag, i) in review.reviewHashtagList" :key="i"
        >#{{ hashtag }}
      </v-col>
    </v-row>
    <!-- 댓글 -->
    <v-row class="ml-auto"
      >댓글 {{ review.reviewCommentCount }} 개 모두 보기</v-row
    >

    <!-- 답글작성폼 -->
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
    this.getReview(this.reviewId);
  },
};
</script>

<style></style>
